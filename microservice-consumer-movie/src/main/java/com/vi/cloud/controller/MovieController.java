package com.vi.cloud.controller;

import com.vi.cloud.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable("id") Long id) {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
        System.out.println("==============");
        System.out.println(serviceInstance);
        System.out.println("==============");
        logger.info(serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":"  + serviceInstance.getPort());
        return restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
    }
}