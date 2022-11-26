package com.oauth.server.OauthServerDemo.repositories;

import com.oauth.server.OauthServerDemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {

    User findOneByUserId(String userId);

}