package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShopRequestResponseDto {
    @SerializedName("code")
    private final int code;
    @SerializedName("requests")
    private final ArrayList<ShopDto>shops;

    public ShopRequestResponseDto(int code, ArrayList<ShopDto> shops) {
        this.code = code;
        this.shops = shops;
    }

    public int getCode() {
        return code;
    }

    public ArrayList<ShopDto> getShops() {
        return shops;
    }
}
