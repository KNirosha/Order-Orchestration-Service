package com.retail.OrderOrchestration.client;

import com.retail.OrderOrchestration.model.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
@Service
public class CatalogServiceClientImpl implements CatalogServiceClient {

    @Autowired
    RestTemplate template;

    @Override
    public Bag getPricing(Bag bag) {
        URI catalogURI = URI.create(CatalogServiceClient.CATALOG_API);
        return template.postForEntity(catalogURI, bag, Bag.class).getBody();
    }
}
