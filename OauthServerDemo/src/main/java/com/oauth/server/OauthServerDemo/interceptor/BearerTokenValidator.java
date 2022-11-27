package com.oauth.server.OauthServerDemo.interceptor;

import com.oauth.server.OauthServerDemo.config.Constants;
import com.oauth.server.OauthServerDemo.config.SecurityContext;
import com.oauth.server.OauthServerDemo.exception.InvalidCredentialsException;
import com.oauth.server.OauthServerDemo.models.User;
import com.oauth.server.OauthServerDemo.repositories.ClientIdSecretRepository;
import com.oauth.server.OauthServerDemo.repositories.UsersRepository;
import com.oauth.server.OauthServerDemo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class BearerTokenValidator implements HandlerInterceptor {

    private final UsersRepository usersRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Entered, {} ", this.getClass() );
        String username = request.getHeader(Constants.X_USER_NAME);
        String authorization = request.getHeader(Constants.X_BEARER_AUTH);
        if(Objects.nonNull(username) && Objects.nonNull(authorization) && Objects.isNull(SecurityContext.userContext.get())) {
            log.info("Processing started, {} ", this.getClass() );
            String token = validateAndReturnTokenFormat(authorization);
            User user = isTokenValid(username, token);
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

    private String validateAndReturnTokenFormat(String authorization) {
        if(!authorization.startsWith("BEARER")){
            throw new InvalidCredentialsException("Invalid username or token!");
        }
        return authorization.split(" ")[1];
    }

    public User isTokenValid(String username, String token) {
        try {
            User user = this.usersRepository.findOneByUserId(username);
            Claims claims = JwtUtil.decodeJwt(token, user.getSalt());
            if (claims.getSubject().equals(claims.getId()) && claims.getId().equals(username)) {
                return user;
            }
            return null;
        } catch (Exception e) {
            throw new InvalidCredentialsException("Exception occurred while validating user login for userId=" + username, e);
        }
    }

}
