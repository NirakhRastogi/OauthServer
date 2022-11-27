package com.oauth.server.OauthServerDemo.repositories;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/token")
    public String testUrl(@RequestParam("accessToken") String accessToken){
        return accessToken;
    }

}