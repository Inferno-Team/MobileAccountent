package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class Response2RequestDto {
    @SerializedName("code")
    private final int code;
    @SerializedName("message")
    private final String message;
    @SerializedName("shope")
    private final ShopDto shop;

    public Response2RequestDto(int code, String message, ShopDto shop) {
        this.code = code;
        this.message = message;
        this.shop = shop;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ShopDto getShop() {
        return shop;
    }
}
