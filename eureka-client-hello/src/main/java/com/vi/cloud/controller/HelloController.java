package com.vi.cloud.controller;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/hello")
    public String index() {

        // discoveryClient.getLocalServiceInstance() 这种方法已经过时

        List<ServiceInstance> serviceInstances = this.discoveryClient.getInstances("EUREKA-CLIENT-HELLO");
        logger.info("==============");
        for (ServiceInstance serviceInstance : serviceInstances) {
            logger.info("host: {}, port: {}, url: {}",
                    serviceInstance.getHost(), serviceInstance.getPort(), serviceInstance.getUri());
        }
        logger.info("==============");
        return "hello world!";
    }

}
