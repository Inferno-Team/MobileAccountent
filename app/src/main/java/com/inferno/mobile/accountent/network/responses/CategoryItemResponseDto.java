package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class CategoryItemResponseDto {
    @SerializedName("item")
    private final CategoryDto category;
    @SerializedName("code")
    private final int code;
    @SerializedName("msg")
    private final String msg;

    public CategoryItemResponseDto( String msg,CategoryDto category, int code) {
        this.category = category;
        this.code = code;
        this.msg = msg;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
