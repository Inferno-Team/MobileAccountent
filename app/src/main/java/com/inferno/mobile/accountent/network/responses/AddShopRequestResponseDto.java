package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class AddShopRequestResponseDto {

    @SerializedName("code")
    private final int code;
    @SerializedName("message")
    private final String message;
    @SerializedName("shope")
    private final ShopDto shop;

    public AddShopRequestResponseDto(int code, String message, ShopDto shop) {
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
