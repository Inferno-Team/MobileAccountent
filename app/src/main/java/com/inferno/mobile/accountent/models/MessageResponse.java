package com.inferno.mobile.accountent.models;

public class MessageResponse {

    private final int code;
    private final String message;
    private final String data;

    public MessageResponse(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
