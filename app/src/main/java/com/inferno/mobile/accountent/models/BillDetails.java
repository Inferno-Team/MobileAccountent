package com.inferno.mobile.accountent.models;


import java.util.ArrayList;
import java.util.Date;

public class BillDetails {
    private final int id;
    private final double check;
    private final Date createdAt;
    private final User cashier;
    private final Shop shop;
    private final ArrayList<BillItem> items;

    public BillDetails(int id, double check, Date createdAt,
                       User cashier, Shop shop,
                       ArrayList<BillItem> items) {
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

    public User getCashier() {
        return cashier;
    }

    public Shop getShop() {
        return shop;
    }

    public ArrayList<BillItem> getItems() {
        return items;
    }
}
