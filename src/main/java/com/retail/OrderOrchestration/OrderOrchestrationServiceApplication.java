package com.retail.OrderOrchestration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableCaching
@EnableCircuitBreaker
@SpringBootApplication
public class OrderOrchestrationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderOrchestrationServiceApplication.class, args);
    }

}

