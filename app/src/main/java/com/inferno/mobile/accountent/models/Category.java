package com.inferno.mobile.accountent.models;

import java.io.Serializable;

public class Category implements Serializable {
    private final int id;
    private final String name;
    private final int count;
    private final double price;
    private final Company company;
    private final String barcode;

    public Category(int id, String name, int count,
                    double price, Company company, String barcode) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.company = company;
        this.barcode = barcode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public Company getCompany() {
        return company;
    }

    public String getBarcode() {
        return barcode;
    }
}
