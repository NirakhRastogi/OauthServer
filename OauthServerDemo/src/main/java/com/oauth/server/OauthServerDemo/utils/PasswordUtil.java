package com.oauth.server.OauthServerDemo.utils;

import com.oauth.server.OauthServerDemo.models.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class PasswordUtil {
    private static final int SALT_LENGTH = 16;

    public static void hashPasswordGenerator(User user, byte[] password) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();

        byte[] salt = new byte[SALT_LENGTH];

        secureRandom.nextBytes(salt);

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt);

        user.setSalt(salt);
        user.setPassword(md.digest(password));
    }

    public static boolean hashPasswordValidator(User user, byte[] receivedPassword) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(user.getSalt());

        byte[] hashedPassword = md.digest(receivedPassword);
        byte[] originalPassword = user.getPassword();

        return Arrays.equals(hashedPassword, originalPassword);

    }

}