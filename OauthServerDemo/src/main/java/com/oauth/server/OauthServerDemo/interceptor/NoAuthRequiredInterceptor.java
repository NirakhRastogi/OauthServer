package com.oauth.server.OauthServerDemo.interceptor;

import com.oauth.server.OauthServerDemo.config.Constants;
import com.oauth.server.OauthServerDemo.config.SecurityContext;
import com.oauth.server.OauthServerDemo.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class NoAuthRequiredInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("Entered, {} ", this.getClass() );
        if(Constants.NO_AUTH_URLS.contains(request.getRequestURI())){
            log.info("Processing completed! result success, {} ", this.getClass());
            SecurityContext.userContext.set(new User());
            return true;
        }
        log.info("Processing completed! result failure, {} ", this.getClass());
        return true;
    }
}
