package com.oauth.server.OauthServerDemo.config;

import com.oauth.server.OauthServerDemo.interceptor.AuthorizationVerificationInterceptor;
import com.oauth.server.OauthServerDemo.interceptor.BearerTokenValidator;
import com.oauth.server.OauthServerDemo.interceptor.NoAuthRequiredInterceptor;
import com.oauth.server.OauthServerDemo.interceptor.UsernamePasswordTokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

    private final UsernamePasswordTokenValidator usernamePasswordTokenValidator;
    private final BearerTokenValidator bearerTokenValidator;
    private final NoAuthRequiredInterceptor noAuthRequiredInterceptor;
    private final AuthorizationVerificationInterceptor authorizationVerificationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noAuthRequiredInterceptor);
        registry.addInterceptor(usernamePasswordTokenValidator);
        registry.addInterceptor(bearerTokenValidator);
        registry.addInterceptor(authorizationVerificationInterceptor);
    }
}