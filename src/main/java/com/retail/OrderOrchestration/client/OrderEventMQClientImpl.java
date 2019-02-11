package com.retail.OrderOrchestration.client;

import com.retail.OrderOrchestration.model.OrderStatusEvent;
import org.springframework.stereotype.Service;

@Service
public class OrderEventMQClientImpl implements OrderEventMQClient {
    @Override
    public void publish(OrderStatusEvent event) {
        // DUMMY IMPLEMENTATION FOR PUBLISH EVENT
        System.out.println("Message published successfully");
    }
}
