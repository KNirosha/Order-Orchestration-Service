package com.retail.OrderOrchestration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OderDetails {
    Long orderNumber;
    Date dateCreated;
    Date dateLastModified;
    OrderStatus orderStatus;
    BillingAddress billingAddress;
    Charges charges;
}
