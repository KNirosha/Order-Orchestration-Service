package com.retail.OrderOrchestration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    public String itemId;
    public int requestedQuantity;
    Double basePrice;

}
