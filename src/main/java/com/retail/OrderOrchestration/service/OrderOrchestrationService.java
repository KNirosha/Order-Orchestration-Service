package com.retail.OrderOrchestration.service;

import com.retail.OrderOrchestration.exception.BusinessException;
import com.retail.OrderOrchestration.model.Bag;
import com.retail.OrderOrchestration.model.OderDetails;

public interface OrderOrchestrationService {

    public Bag getPricing(Bag bag);

    public Bag getPricingAndTax(Bag bag) throws BusinessException;

    public Double getTaxRates(String zipcode);

    public OderDetails checkout(Bag bag) throws BusinessException;

}
