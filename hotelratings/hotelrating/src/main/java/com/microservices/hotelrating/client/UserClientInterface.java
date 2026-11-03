package com.microservices.hotelrating.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICES", fallback = UserClientFallback.class)
public interface UserClientInterface {
    @GetMapping("isUserExists/{userId")
    public boolean isUserExists(@PathVariable String id);

}
