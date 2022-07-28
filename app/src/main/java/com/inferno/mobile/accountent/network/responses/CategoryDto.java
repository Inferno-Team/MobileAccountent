package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class CategoryDto {
    @SerializedName("id")
    private final int id;
    @SerializedName("category_name")
    private final String name;
    @SerializedName("stock_count")
    private final int count;
    @SerializedName("price")
    private final double price;
    @SerializedName("comp_name")
    private final CompanyDto company;

    @SerializedName("barcode")
    private final String barcode;

    public CategoryDto(int id, String name, int count,
                       double price, CompanyDto company, String barcode) {
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

    public CompanyDto getCompany() {
        return company;
    }

    public String getBarcode() {
        return barcode;
    }
}
