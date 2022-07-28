package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class AddManagerResponseDto {
    @SerializedName("code")
    private final int code;
    @SerializedName("message")
    private final String message;
    @SerializedName("user")
    private final UserDto user;

    public AddManagerResponseDto(int code, String message, UserDto user) {
        this.code = code;
        this.message = message;
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public UserDto getUser() {
        return user;
    }
}
