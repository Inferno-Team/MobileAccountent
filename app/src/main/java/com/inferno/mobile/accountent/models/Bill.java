package com.inferno.mobile.accountent.models;


import java.util.ArrayList;

public class Bill {


    private final double check;
    private final Shop shop;
    private final ArrayList<BillItem> items;

    public Bill(double check, Shop shop, ArrayList<BillItem> items) {
        this.check = check;
        this.shop = shop;
        this.items = items;
    }

    public double getCheck() {
        return check;
    }

    public Shop getShop() {
        return shop;
    }

    public ArrayList<BillItem> getItems() {
        return items;
    }
}
