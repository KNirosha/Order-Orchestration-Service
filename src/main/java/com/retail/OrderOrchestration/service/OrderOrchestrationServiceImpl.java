package com.retail.OrderOrchestration.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.retail.OrderOrchestration.client.CatalogServiceClient;
import com.retail.OrderOrchestration.client.CheckoutWSDLClient;
import com.retail.OrderOrchestration.client.OrderEventMQClient;
import com.retail.OrderOrchestration.client.TaxServiceClient;
import com.retail.OrderOrchestration.exception.BusinessException;
import com.retail.OrderOrchestration.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderOrchestrationServiceImpl implements OrderOrchestrationService {

    @Autowired
    CatalogServiceClient catalogServiceClient;
    @Autowired
    TaxServiceClient taxServiceClient;
    @Autowired
    CheckoutWSDLClient checkoutWSDLClient;
    @Autowired
    OrderEventMQClient orderEventMQClient;
    @Value("${app.config.nexus.tax}")
    Double nexusDefaultTax;


    @Override
    public Bag getPricing(Bag bag) {
        return catalogServiceClient.getPricing(bag);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getPricingAndTaxFallback")
    public Bag getPricingAndTax(Bag bag) throws BusinessException {
        Bag pricedBag = getPricing(bag);
        Double taxRate = getTaxRates(pricedBag.getBillingAddress().getZipcode());
        Double tax = computeTax(pricedBag.getCharges().getBasePrice(), taxRate);
        pricedBag.getCharges().setTaxRate(taxRate);
        pricedBag.getCharges().setTaxPrice(tax);
        return pricedBag;
    }

    @Override
    @Cacheable (key = "zipcode", value = "tax_rates")
    @HystrixCommand(fallbackMethod = "getTaxRatesFallback")
    public Double getTaxRates(String zipcode) {
        return taxServiceClient.getTaxRate(zipcode);
    }

    @Override
    public OderDetails checkout(Bag bag) throws BusinessException {
        OderDetails orderDetails = checkoutWSDLClient.checkout(bag);
        OrderStatusEvent event = OrderStatusEvent.builder().
                oderDetails(orderDetails).
                recipientsList(Arrays.asList(orderDetails.getBillingAddress().getEmail())).
                build();
        switch (orderDetails.getOrderStatus()) {
            case CREATED:
                event.setEmailTemplates(EmailTemplates.ORDER_SUCCESS);
                orderEventMQClient.publish(event);
                System.out.println("Order success event");
                break;
            case FAILED:
                event.setEmailTemplates(EmailTemplates.ORDER_FAILED);
                orderEventMQClient.publish(event);
                System.out.println("Order Failure event");
                break;
            case REJECTED:
                event.setEmailTemplates(EmailTemplates.ORDER_REJECTED);
                orderEventMQClient.publish(event);
                System.out.println("Order Rejected event");
                break;

        }
        return orderDetails;
    }


    public Bag getPricingAndTaxFallback(Bag bag) throws BusinessException {

        // Handling a dummy fallback event

        Item item1 = Item.builder().itemId("ITEM1").basePrice(23.5).requestedQuantity(1).build();
        Item item2 = Item.builder().itemId("ITEM2").basePrice(233.5).requestedQuantity(1).build();
        List<Item> items = new ArrayList<>();
                items.add(item1);
                items.add(item2);

        BillingAddress billingAddress = BillingAddress.builder()
                                        .city("SANCARLOS")
                                        .countryCode("USA")
                                        .flatNo("12A")
                                        .zipcode("123456")
                                        .state("SFO")
                                        .email("test@gmail.com").build();
        Charges charges = Charges.builder()
                                        .handlingCharges(12.5)
                                        .shippingCharges(26)
                                        .basePrice(267)
                                        .taxPrice(45)
                                        .taxRate(nexusDefaultTax)
                                        .totalPrice(359).build();
        Bag pricedBag = Bag.builder()
                        .bagId("12345")
                        .countryCode("USA")
                        .currency("USD")
                        .status("PRICED")
                        .itemsCount(2)
                        .items(items)
                        .billingAddress(billingAddress)
                        .charges(charges)
                        .build();

        return pricedBag;
        //throw new BusinessException("Catalog-service invocation failed");
    }

    public Double getTaxRatesFallback(String zipcode) {
        return new Double(nexusDefaultTax);

    }

    private Double computeTax(Double basePrice, Double taxRate) {
        return (taxRate * basePrice) / 100;
    }

}
