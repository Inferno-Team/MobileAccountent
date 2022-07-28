package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BillDto {

    @SerializedName("check")
    private final double check;
    @SerializedName("shope")
    private final ShopDto shop;
    @SerializedName("items")
    private final ArrayList<BillItemDto>items;

    public BillDto(double check, ShopDto shop, ArrayList<BillItemDto> items) {
        this.check = check;
        this.shop = shop;
        this.items = items;
    }

    public double getCheck() {
        return check;
    }

    public ShopDto getShop() {
        return shop;
    }

    public ArrayList<BillItemDto> getItems() {
        return items;
    }
}
