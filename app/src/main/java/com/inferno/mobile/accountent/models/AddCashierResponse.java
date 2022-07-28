package com.inferno.mobile.accountent.models;

public class AddCashierResponse {
    private final int code;
    private final String message;

    public AddCashierResponse(int code, String message) {
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
