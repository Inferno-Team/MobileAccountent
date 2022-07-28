package com.inferno.mobile.accountent.models;

public class AddShopRequestResponse {

    private final int code;
    private final String msg;
    private final Shop shop;

    public AddShopRequestResponse(int code, String msg, Shop shop) {
        this.code = code;
        this.msg = msg;
        this.shop = shop;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Shop getShop() {
        return shop;
    }
}
