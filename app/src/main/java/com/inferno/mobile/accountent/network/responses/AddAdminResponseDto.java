package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class AddAdminResponseDto {

    @SerializedName("code")
    private final int statusCode;
    @SerializedName("msg")
    private final String message;
    @SerializedName("user")
    private final UserDto admin;

    public AddAdminResponseDto(int statusCode, String message, UserDto user) {
        this.statusCode = statusCode;
        this.message = message;
        this.admin = user;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public UserDto getAdmin() {
        return admin;
    }
}
