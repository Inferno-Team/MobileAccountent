package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserDto implements Serializable {
    @SerializedName("user_name")
    private final String userName;
    @SerializedName("email")
    private final String email;
    @SerializedName("phone")
    private final String phone;
    @SerializedName("type")
    private final String type;
    @SerializedName("id")
    private final int id;

    public UserDto(int id,String userName, String email, String phone, String type) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.id = id;
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

    public String getType() {
        return type;
    }
}
