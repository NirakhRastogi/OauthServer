package com.oauth.server.OauthServerDemo.resources;

import com.oauth.server.OauthServerDemo.config.Constants;
import com.oauth.server.OauthServerDemo.models.ClientIdSecret;
import com.oauth.server.OauthServerDemo.services.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthResource {

    private final OauthService oauthService;

    @PostMapping("")
    public ResponseEntity<Void> createLogin(@RequestHeader(value = Constants.X_USER_NAME) String user,
                                            @RequestHeader(value = Constants.X_AUTHORIZATION) String password,
                                            @RequestParam(value = "expiresAt")LocalDate expiresAt){

        return this.oauthService.generateClientIdAndSecret(user, expiresAt);

    }

    @GetMapping("")
    public ResponseEntity<Void> redirectUserToLogin(@RequestHeader(value = Constants.X_CLIENT_ID) String user,
                                            @RequestHeader(value = Constants.X_SECRET) String secret){

        return this.oauthService.redirectUserToLogin(user);

    }

}