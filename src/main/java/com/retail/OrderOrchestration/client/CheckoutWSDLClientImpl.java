package com.retail.OrderOrchestration.client;

import com.retail.OrderOrchestration.model.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CheckoutWSDLClientImpl implements CheckoutWSDLClient {
    @Override
    public OderDetails checkout(Bag bag) {
        // dummy implementation for order service
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
                .taxRate(8.5)
                .totalPrice(359)
                .build();
        return OderDetails.builder()
                .orderNumber(new Long(12345))
                .orderStatus(OrderStatus.CREATED)
                .dateLastModified(new Date())
                .dateCreated(new Date())
                .billingAddress(billingAddress)
                .charges(charges)
                .build();

    }
}
