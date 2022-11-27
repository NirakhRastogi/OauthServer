package com.oauth.server.OauthServerDemo.interceptor;

import com.oauth.server.OauthServerDemo.config.Constants;
import com.oauth.server.OauthServerDemo.config.SecurityContext;
import com.oauth.server.OauthServerDemo.exception.InvalidCredentialsException;
import com.oauth.server.OauthServerDemo.models.User;
import com.oauth.server.OauthServerDemo.repositories.ClientIdSecretRepository;
import com.oauth.server.OauthServerDemo.repositories.UsersRepository;
import com.oauth.server.OauthServerDemo.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class UsernamePasswordTokenValidator implements HandlerInterceptor {

    private final UsersRepository usersRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Entered, {} ", this.getClass() );
        String username = request.getHeader(Constants.X_USER_NAME);
        String authorization = request.getHeader(Constants.X_AUTHORIZATION);
        if(Objects.nonNull(username) && Objects.nonNull(authorization) && Objects.isNull(SecurityContext.userContext.get())) {
            log.info("Processing started, {} ", this.getClass() );
            User user = isTokenValid(username, authorization);
            if (user == null) {
                log.info("Processing completed, result failure, {} ", this.getClass() );
                throw new InvalidCredentialsException("Either username or password is invalid.");
            } else {
                SecurityContext.userContext.set(user);
                log.info("Processing completed! result success, {} ", this.getClass() );
                return true;
            }
        }
        log.info("Force Exiting, {} ", this.getClass() );
        return true;
    }

    public User isTokenValid(String username, String password) {
        try {
            User user = this.usersRepository.findOneByUserId(username);
            boolean result = PasswordUtil.hashPasswordValidator(user, Base64.getDecoder().decode(password));
            if (result) {
                return user;
            }
            return null;
        } catch (Exception e) {
            throw new InvalidCredentialsException("Exception occurred while validating user login for userId=" + username, e);
        }
    }
}