package com.example.circularinventory.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String salt;

    @Column(name= "session_token")
    @Nullable
    private String sessionToken;

    @Column(name = "token_expiration_time")
    @Nullable
    private Instant tokenExpirationTime;

    public Account(String username, String email, String password, String salt) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }

    public Account() {

    }

    public String getSalt() {
        return salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Nullable
    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(@Nullable String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @Nullable
    public Instant getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(@Nullable Instant tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }
}
