package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class LoginResponseDto {
    @SerializedName("token")
    private final String token;
    @SerializedName("message")
    private final String message;
    @SerializedName("type")
    private final String type;

    public LoginResponseDto(String token, String message, String type) {
        this.token = token;
        this.message = message;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
