package com.retail.OrderOrchestration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Charges {
    double basePrice;
    double taxRate;
    double taxPrice;
    double shippingCharges;
    double handlingCharges;
    double totalPrice;
}
