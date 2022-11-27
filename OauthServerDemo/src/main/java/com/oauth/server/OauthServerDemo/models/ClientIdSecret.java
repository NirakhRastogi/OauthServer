package com.oauth.server.OauthServerDemo.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "client_id_secret")
public class ClientIdSecret {

    private String userId;
    @Id
    private String clientId;
    private byte[] secret;
    private LocalDate expiresAt;
    private LocalDate createdAt;
    @Column
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> roles;
}