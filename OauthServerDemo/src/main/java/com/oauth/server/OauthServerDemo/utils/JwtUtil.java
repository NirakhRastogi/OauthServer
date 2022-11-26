package com.oauth.server.OauthServerDemo.utils;

import com.oauth.server.OauthServerDemo.config.Constants;
import com.oauth.server.OauthServerDemo.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class JwtUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static String generateJWTToken(User user){
        return Jwts.builder()
                .setSubject(user.getUserId())
                .setId(user.getUserId())
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(LocalDateTime.now().plusDays(Constants.JWT_EXPIRE_TIME).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, user.getSalt())
                .compact();
    }

    public static Claims decodeJwt(String token, byte[] secret){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private static Map<String, Object> convertRoleToClaims(User user) {
        return Map.of("ROLES", user.getRoles());
    }

}
