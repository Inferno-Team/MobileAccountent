package com.inferno.mobile.accountent.models;

import com.google.gson.annotations.SerializedName;

public class Response2Request {
    private final int code;
    private final String message;
    private final Shop shop;

    public Response2Request(int code, String message, Shop shop) {
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

    public Shop getShop() {
        return shop;
    }
}
