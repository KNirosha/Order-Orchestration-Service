package com.retail.OrderOrchestration.client;

import com.retail.OrderOrchestration.model.OrderStatusEvent;

public interface OrderEventMQClient {

    public void publish(OrderStatusEvent event);
}
