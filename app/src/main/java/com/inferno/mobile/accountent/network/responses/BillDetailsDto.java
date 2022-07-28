package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class BillDetailsDto {
    @SerializedName("id")
    private final int id;
    @SerializedName("check")
    private final double check;
    @SerializedName("created_at")
    private final Date createdAt;
    @SerializedName("cashier")
    private final UserDto cashier;
    @SerializedName("shope")
    private final ShopDto shop;
    @SerializedName("items")
    private final ArrayList<BillItemDto> items;

    public BillDetailsDto(int id, double check, Date createdAt,
                          UserDto cashier, ShopDto shop,
                          ArrayList<BillItemDto> items) {
        this.id = id;
        this.check = check;
        this.createdAt = createdAt;
        this.cashier = cashier;
        this.shop = shop;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public double getCheck() {
        return check;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public UserDto getCashier() {
        return cashier;
    }

    public ShopDto getShop() {
        return shop;
    }

    public ArrayList<BillItemDto> getItems() {
        return items;
    }
}
