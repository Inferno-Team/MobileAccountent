package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class AddCategoryResponseDto {
    @SerializedName("code")
    private final int code;
    @SerializedName("msg")
    private final String message;

    @SerializedName("cat")
    private final CategoryDto category;

    public AddCategoryResponseDto(int code, String message, CategoryDto category) {
        this.code = code;
        this.message = message;
        this.category = category;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public CategoryDto getCategory() {
        return category;
    }
}
