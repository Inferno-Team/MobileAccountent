package com.inferno.mobile.accountent.models;

public class CategoryItemResponse {

    private final String msg;
    private final Category category;
    private final int code;

    public CategoryItemResponse(String msg, Category category, int code) {
        this.msg = msg;
        this.category = category;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Category getCategory() {
        return category;
    }

    public int getCode() {
        return code;
    }
}
