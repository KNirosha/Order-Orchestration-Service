package com.retail.OrderOrchestration.resource;

import com.retail.OrderOrchestration.exception.BusinessException;
import com.retail.OrderOrchestration.model.Bag;
import com.retail.OrderOrchestration.model.OderDetails;
import com.retail.OrderOrchestration.service.OrderOrchestrationService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "checkout order" , response = OderDetails.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = OderDetails.class, message = "price calculation executed successfully"),
            @ApiResponse(code = 400, message = "Bad request. Something wrong was sent from the client."),
            @ApiResponse(code = 401, message = "Unauthorized."),
            @ApiResponse(code = 500, message = "Business exception")})
    @PostMapping("/pricing/v1")
    public Bag getPricingDetails(@RequestBody Bag bag) throws BusinessException {
        return orderOrchestrationService.getPricingAndTax(bag);
    }

    @ApiOperation(value = "checkout order" , response = OderDetails.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, response = OderDetails.class, message = "order created successfully"),
            @ApiResponse(code = 400, message = "Bad request. Something wrong was sent from the client."),
            @ApiResponse(code = 401, message = "Unauthorized."),
            @ApiResponse(code = 500, message = "Business exception")})
    @PostMapping("/checkout/v1")
    public OderDetails checkoutOrder(@RequestBody Bag bag) throws BusinessException {
        return orderOrchestrationService.checkout(bag);
    }

}
