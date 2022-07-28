package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class AddCashierResponseDto {
    @SerializedName("code")
    private final int code;
    @SerializedName("message")
    private final String message;

    public AddCashierResponseDto(int code, String message) {
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
