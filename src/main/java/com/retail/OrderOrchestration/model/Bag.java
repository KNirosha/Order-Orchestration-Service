package com.retail.OrderOrchestration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bag {
    public String bagId;
    public String countryCode;
    public String currency;
    public int itemsCount;
    public String status;
    public List<Item> items;
    public Charges charges;
    public BillingAddress billingAddress;

}
