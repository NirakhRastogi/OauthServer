package com.oauth.server.OauthServerDemo.models;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_details")
public class User {
    @Id
    private String userId;
    private byte[] password;
    private byte[] salt;
    private String firstName;
    private String lastName;
    @Column
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> roles;
}