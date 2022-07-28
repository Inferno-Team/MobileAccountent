package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class AddItemResponseDto {
    @SerializedName("code")
    private final int code;
    @SerializedName("message")
    private final String message;

    public AddItemResponseDto(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
