package com.retail.OrderOrchestration.client;

import com.retail.OrderOrchestration.model.Bag;
import org.springframework.web.bind.annotation.RequestBody;

public interface CatalogServiceClient {

    public static final String CATALOG_API = "http://localhost:catalog-service/api/";

    public Bag getPricing(@RequestBody Bag bag);

}
