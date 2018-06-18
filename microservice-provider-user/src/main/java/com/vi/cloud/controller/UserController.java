package com.vi.cloud.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.vi.cloud.entity.User;
import com.vi.cloud.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${randomValue.name}")
    private String name;

    @Value("${randomValue.age}")
    private int age;


    @GetMapping("/simple/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping("/eureka-instance")
    public String serviceUrl() {
        InstanceInfo instanceInfo = this.eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
        return instanceInfo.getHomePageUrl();
    }

    @GetMapping("/instance-info")
    public List<ServiceInstance> showInfo() {
//        discoveryClient.getLocalServiceInstance() 这种方法已经过时
        List<ServiceInstance> serviceInstances = this.discoveryClient.getInstances("MICROSERVICE-PROVIDER-USER");
        logger.info("==============");
        for (ServiceInstance serviceInstance : serviceInstances) {
            logger.info("host: {}, port: {}, url: {}",
                    serviceInstance.getHost(), serviceInstance.getPort(), serviceInstance.getUri());
        }
        logger.info("==============");
        return serviceInstances;
    }

}
