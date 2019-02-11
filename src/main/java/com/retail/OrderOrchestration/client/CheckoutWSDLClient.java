package com.retail.OrderOrchestration.client;

import com.retail.OrderOrchestration.model.Bag;
import com.retail.OrderOrchestration.model.OderDetails;

public interface CheckoutWSDLClient {

    public OderDetails checkout(Bag bag);

}
