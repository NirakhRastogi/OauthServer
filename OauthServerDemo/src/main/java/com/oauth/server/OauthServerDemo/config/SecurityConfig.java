package com.oauth.server.OauthServerDemo.config;

import com.oauth.server.OauthServerDemo.interceptor.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

    private final UsernamePasswordTokenValidator usernamePasswordTokenValidator;
    private final BearerTokenValidator bearerTokenValidator;
    private final NoAuthRequiredInterceptor noAuthRequiredInterceptor;
    private final AuthorizationVerificationInterceptor authorizationVerificationInterceptor;
    private final ClientIdSecretValidator clientIdSecretValidator;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noAuthRequiredInterceptor);
        registry.addInterceptor(usernamePasswordTokenValidator);
        registry.addInterceptor(bearerTokenValidator);
        registry.addInterceptor(clientIdSecretValidator);
        registry.addInterceptor(authorizationVerificationInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*");
    }
}