package com.retail.OrderOrchestration.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Service
public class TaxServiceClientImpl implements TaxServiceClient {

    @Autowired
    RestTemplate template;

    @Override
    public Double getTaxRate(String zipcode) {
        Map<String, String> params = new HashMap<>();
        params.put("zipcode", zipcode);
        return template.getForEntity(TaxServiceClient.TAX_API, Double.class, params).getBody();
    }
}
