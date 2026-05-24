package com.foodhunt.auth_service.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public/hello")
    public String publicApi(){
       return "public api";
    }
    @GetMapping("/private/hello")
    public String privateApi(){
        return "Private API";
    }
}
