package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class BillItemDto {
    @SerializedName("count")
    private final int count;
    @SerializedName("cat_item")
    private final CategoryDto category;

    public BillItemDto(int count, CategoryDto category) {
        this.count = count;
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public CategoryDto getCategory() {
        return category;
    }
}
