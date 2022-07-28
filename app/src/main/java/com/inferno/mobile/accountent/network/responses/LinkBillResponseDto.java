package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class LinkBillResponseDto {
    @SerializedName("code")
    private final int code;
    @SerializedName("message")
    private final String message;
    @SerializedName("bill")
    private final BillDto billDto;

    public LinkBillResponseDto(int code, String message, BillDto billDto) {
        this.code = code;
        this.message = message;
        this.billDto = billDto;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public BillDto getBillDto() {
        return billDto;
    }
}
