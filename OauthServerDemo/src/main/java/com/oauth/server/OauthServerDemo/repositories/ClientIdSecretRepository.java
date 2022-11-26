package com.oauth.server.OauthServerDemo.repositories;

import com.oauth.server.OauthServerDemo.models.ClientIdSecret;
import com.oauth.server.OauthServerDemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientIdSecretRepository extends JpaRepository<ClientIdSecret, String> {

    User findOneByUserId(String userId);

}