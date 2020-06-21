package com.vgrupper.demo.models;

import com.vgrupper.demo.entity.User;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String jwt;

    private final User username;

    public AuthenticationResponse(String jwt, User username) {
        this.jwt = jwt;
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public User getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Y account has been created! {" +
                "Your JWT HERE : '" + jwt + '\'' +
                ",AND YOUR username is " + username +
                '}';
    }
}
