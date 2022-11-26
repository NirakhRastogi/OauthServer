package com.oauth.server.OauthServerDemo.interceptor;

import com.oauth.server.OauthServerDemo.config.SecurityContext;
import com.oauth.server.OauthServerDemo.exception.InvalidCredentialsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
@Slf4j
public class AuthorizationVerificationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Entered, {} ", this.getClass() );
        if(Objects.isNull(SecurityContext.userContext.get())){
            log.info("Processing completed! result failure, {} ", this.getClass());
            throw new InvalidCredentialsException("No auth provided..");
        }
        log.info("Processing completed! result success, {} ", this.getClass());
        return true;
    }
}
