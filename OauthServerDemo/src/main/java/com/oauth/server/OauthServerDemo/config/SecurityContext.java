package com.oauth.server.OauthServerDemo.config;

import com.oauth.server.OauthServerDemo.models.User;
import lombok.Getter;
import lombok.Setter;

public class SecurityContext {

    public static ThreadLocal<User> userContext = ThreadLocal.withInitial(() -> null);

}
