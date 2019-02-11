package com.retail.OrderOrchestration.client;

public interface TaxServiceClient {

    public static final String TAX_API = "http://localhost:catalog-service/api/";

    public Double getTaxRate(String zipcode);

}
