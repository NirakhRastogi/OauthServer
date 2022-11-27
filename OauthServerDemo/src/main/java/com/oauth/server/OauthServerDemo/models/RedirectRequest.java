package com.oauth.server.OauthServerDemo.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedirectRequest {

    private String redirectUrl;
    private List<String> roles;

}