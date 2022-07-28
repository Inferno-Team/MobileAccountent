package com.inferno.mobile.accountent.models;

public class AddCategoryResponse {
    private final int code;
    private final String msg;
    private final Category category;

    public AddCategoryResponse(int code, String msg, Category category) {
        this.code = code;
        this.msg = msg;
        this.category = category;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Category getCategory() {
        return category;
    }
}
