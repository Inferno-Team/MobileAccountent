package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class RemoveAdminResponseDto {
    @SerializedName("code")
    private final int code;
    @SerializedName("message")
    private final String message;
    @SerializedName("user")
    private final UserDto admin;

    public RemoveAdminResponseDto(int code, String message, UserDto user) {
        this.code = code;
        this.message = message;
        this.admin = user;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public UserDto getAdmin() {
        return admin;
    }
}
