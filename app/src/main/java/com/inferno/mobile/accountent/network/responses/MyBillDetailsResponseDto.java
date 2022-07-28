package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyBillDetailsResponseDto {
    @SerializedName("code")
    private final int code;

    @SerializedName("bills")
    private final ArrayList<BillDetailsDto> details;

    @SerializedName("message")
    private final String message;

    public MyBillDetailsResponseDto(int code, ArrayList<BillDetailsDto> details, String message) {
        this.code = code;
        this.details = details;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public ArrayList<BillDetailsDto> getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }
}
