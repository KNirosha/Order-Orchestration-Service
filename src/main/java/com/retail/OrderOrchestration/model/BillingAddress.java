package com.retail.OrderOrchestration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillingAddress {
    String flatNo;
    String street;
    String city;
    String state;
    String countryCode;
    String zipcode;
    String email;
}
