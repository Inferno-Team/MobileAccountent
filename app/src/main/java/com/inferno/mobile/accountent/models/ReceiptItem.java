package com.inferno.mobile.accountent.models;

import java.io.Serializable;

public class ReceiptItem implements Serializable {
    private final String categoryName;
    private final double price;
    private final int count;
    private final double fullPrice;
    private final String barcode;

    public ReceiptItem(String categoryName, double price, int count, String barcode) {
        this.categoryName = categoryName;
        this.price = price;
        this.count = count;
        this.fullPrice = price * count;
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public double getFullPrice() {
        return fullPrice;
    }
}
