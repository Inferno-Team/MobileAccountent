package com.inferno.mobile.accountent.models;

public class AddItemResponse {
    private final int code;
    private final String message;

    public AddItemResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
