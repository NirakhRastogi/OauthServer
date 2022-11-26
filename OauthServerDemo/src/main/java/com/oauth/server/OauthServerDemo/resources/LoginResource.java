package com.oauth.server.OauthServerDemo.resources;

import com.oauth.server.OauthServerDemo.config.Constants;
import com.oauth.server.OauthServerDemo.config.SecurityContext;
import com.oauth.server.OauthServerDemo.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoginResource {

    private final LoginService loginService;

    @PostMapping("/signup")
    public void createLogin(@RequestHeader(value = Constants.X_USER_NAME) String user, @RequestHeader(value = Constants.X_AUTHORIZATION) String password, @RequestBody List<String> roles){

        this.loginService.createUserLogin(user, password, roles);

    }

    @GetMapping("/login")
    public String createLogin(){
        return this.loginService.exchangeUsernameAndPassword(SecurityContext.userContext.get());
    }

}