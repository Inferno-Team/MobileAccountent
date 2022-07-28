package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class LogoutResponseDto {
    @SerializedName("code")
    private final int statusCode;

    @SerializedName("msg")
    private final String message;

    public LogoutResponseDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
