package com.inferno.mobile.accountent.models;


import java.util.ArrayList;

public class ShopRequestResponse {
    private final int code;
    private final ArrayList<Shop> shops;

    public ShopRequestResponse(int code, ArrayList<Shop> shops) {
        this.code = code;
        this.shops = shops;
    }

    public int getCode() {
        return code;
    }

    public ArrayList<Shop> getShops() {
        return shops;
    }
}
