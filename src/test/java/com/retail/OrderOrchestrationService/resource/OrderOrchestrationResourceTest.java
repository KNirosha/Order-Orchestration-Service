package com.retail.OrderOrchestrationService.resource;

import com.retail.OrderOrchestration.OrderOrchestrationServiceApplication;
import com.retail.OrderOrchestration.model.Bag;
import com.retail.OrderOrchestration.model.BillingAddress;
import com.retail.OrderOrchestration.model.Charges;
import com.retail.OrderOrchestration.model.Item;
import com.retail.OrderOrchestration.resource.OrderOrchestrationResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderOrchestrationServiceApplication.class)
@AutoConfigureMockMvc
public class OrderOrchestrationResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderOrchestrationResource orderOrchestrationResource;


    @Test
    public void smokeTest() throws Exception {
        assertThat(orderOrchestrationResource).isNotNull();
    }

    @Test
    public void testCheckoutAPINegativeCase() throws Exception {
        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/checkout/v2", createBag())).andExpect(status().isNotFound());
    }
    @Test
    public void testGetPricingAPINegativeCase() throws Exception {
        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/pricing/v2", createBag())).andExpect(status().isNotFound());
    }


    private Bag createBag() {
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
                .taxRate(8.5)
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
    }
}
