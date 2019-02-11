package com.retail.OrderOrchestration.resource;

import com.retail.OrderOrchestration.exception.BusinessException;
import com.retail.OrderOrchestration.model.Bag;
import com.retail.OrderOrchestration.model.OderDetails;
import com.retail.OrderOrchestration.service.OrderOrchestrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/api")
public class OrderOrchestrationResource {

    @Autowired
    OrderOrchestrationService orderOrchestrationService;

    @PostMapping("/pricing/v1")
    public Bag getPricingDetails(@RequestBody Bag bag) throws BusinessException {
        return orderOrchestrationService.getPricingAndTax(bag);
    }

    @PostMapping("/checkout/v1")
    public OderDetails checkoutOrder(@RequestBody Bag bag) throws BusinessException {
        return orderOrchestrationService.checkout(bag);
    }

}
