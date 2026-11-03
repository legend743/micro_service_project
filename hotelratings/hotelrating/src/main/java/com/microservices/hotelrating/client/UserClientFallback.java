package com.microservices.hotelrating.client;

import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.PathVariable;

@Component
public class UserClientFallback implements UserClientInterface {
    @Override
    public boolean isUserExists(@PathVariable String id) {
        return false;
    }

}
