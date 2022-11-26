package com.oauth.server.OauthServerDemo.services;

import com.oauth.server.OauthServerDemo.config.Constants;
import com.oauth.server.OauthServerDemo.config.SecurityContext;
import com.oauth.server.OauthServerDemo.models.ClientIdSecret;
import com.oauth.server.OauthServerDemo.models.User;
import com.oauth.server.OauthServerDemo.repositories.ClientIdSecretRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final ClientIdSecretRepository clientIdSecretRepository;

    public ResponseEntity<Void> generateClientIdAndSecret(String userId, LocalDate expiresAt) {

        User user = SecurityContext.userContext.get();
        String clientId = UUID.randomUUID().toString().replaceAll("-", "");
        String secret = getClientSecret(user, expiresAt);

        this.clientIdSecretRepository.save(ClientIdSecret.builder()
        .createdAt(LocalDate.now())
        .expiresAt(expiresAt)
        .id(UUID.randomUUID())
        .userId(userId)
        .build());

        return ResponseEntity.ok().header(Constants.X_CLIENT_ID, clientId)
                .header(Constants.X_SECRET,secret)
                .build();

    }

    private String getClientSecret(User user, LocalDate expiresAt) {

        return Jwts.builder()
                .setIssuer(Constants.SERVICE_NAME)
                .setId(user.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(expiresAt.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, Constants.JWT_SECRET)
                .compact();

    }

    public ResponseEntity<Void> redirectUserToLogin(String user) {
        String redirectUrl = "";
        return ResponseEntity
        .status(HttpStatus.TEMPORARY_REDIRECT)
        .header(Constants.X_LOCATION, redirectUrl)
        .build();
    }
}
