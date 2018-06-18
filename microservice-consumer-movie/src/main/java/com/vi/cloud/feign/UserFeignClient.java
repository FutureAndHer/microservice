package com.vi.cloud.feign;

import com.vi.cloud.entity.User;
import com.vi.config.SelfConfiguration;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "microservice-provider-user", configuration = SelfConfiguration.class)
public interface UserFeignClient {

/*
    @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
*/

    @RequestLine("GET /simple/{id}")
    public User findById(@Param("id") Long id);

}
