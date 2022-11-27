package com.oauth.server.OauthServerDemo.repositories;

import com.oauth.server.OauthServerDemo.models.ClientIdSecret;
import com.oauth.server.OauthServerDemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientIdSecretRepository extends JpaRepository<ClientIdSecret, String> {

    List<ClientIdSecret> findAllByUserId(String userId);
    ClientIdSecret findOneByClientId(String clientId);

}