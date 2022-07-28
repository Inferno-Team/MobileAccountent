package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class CompanyDto {
    @SerializedName("id")
    private final int id;
    @SerializedName("name")
    private final String name;
    @SerializedName("cat_count")
    private final int catCount;

    public int getCatCount() {
        return catCount;
    }

    public CompanyDto(int id, String name, int catCount) {
        this.id = id;
        this.name = name;
        this.catCount = catCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
