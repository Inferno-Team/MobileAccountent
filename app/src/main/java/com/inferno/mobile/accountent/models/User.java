package com.inferno.mobile.accountent.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    private final int id;
    private final String userName;
    private final String email;
    private final String phone;
    private final UserType type;

    public User(int id, String userName, String email, String phone, UserType type) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public UserType getType() {
        return type;
    }
}
