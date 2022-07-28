package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class MessageResponseDto {
    @SerializedName("code")
    private final int code;
    @SerializedName("msg")
    private final String message;
   @SerializedName("data")
    private final String data;

    public MessageResponseDto(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
