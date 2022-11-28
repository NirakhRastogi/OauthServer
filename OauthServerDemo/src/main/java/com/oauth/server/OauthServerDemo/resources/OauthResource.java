package com.oauth.server.OauthServerDemo.resources;

import com.oauth.server.OauthServerDemo.config.Constants;
import com.oauth.server.OauthServerDemo.models.RedirectRequest;
import com.oauth.server.OauthServerDemo.services.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthResource {

    private final OauthService oauthService;

    @PostMapping("")
    public ResponseEntity<Void> createLogin(@RequestHeader(value = Constants.X_USER_NAME) String user,
                                            @RequestHeader(value = Constants.X_BEARER_AUTH) String password,
                                            @RequestParam(value = "expiresAt")
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                            LocalDate expiresAt) {

        return this.oauthService.generateClientIdAndSecret(user, expiresAt);

    }

    @PostMapping("/access-token")
    public ResponseEntity<String> redirectUserToLogin(@RequestHeader(value = Constants.X_CLIENT_ID) String clientId,
                                                            @RequestHeader(value = Constants.X_AUTHORIZATION) String password,
                                                            @RequestHeader(value = Constants.X_USER_NAME) String username,
                                                            @RequestBody RedirectRequest redirectRequest) {

        return this.oauthService.redirectUserToLogin(clientId, redirectRequest);

    }

}