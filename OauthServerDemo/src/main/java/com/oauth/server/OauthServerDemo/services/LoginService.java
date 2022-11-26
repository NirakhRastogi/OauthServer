package com.oauth.server.OauthServerDemo.services;

import com.oauth.server.OauthServerDemo.models.User;
import com.oauth.server.OauthServerDemo.repositories.ClientIdSecretRepository;
import com.oauth.server.OauthServerDemo.repositories.UsersRepository;
import com.oauth.server.OauthServerDemo.utils.JwtUtil;
import com.oauth.server.OauthServerDemo.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UsersRepository usersRepository;

    public void createUserLogin(String userId, String password, List<String> roles) {

        try {
            User user = User.builder()
                .userId(userId)
                .firstName("")
                    .roles(roles)
                .lastName("").build();
            PasswordUtil.hashPasswordGenerator(user, Base64.getDecoder().decode(password));
            this.usersRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while creating user login.", e);
        }

    }

    public String exchangeUsernameAndPassword(User user){
        return JwtUtil.generateJWTToken(user);
    }

}
