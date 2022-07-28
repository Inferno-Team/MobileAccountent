package com.inferno.mobile.accountent.models;


public class LogoutResponse {

    private final int statusCode;

    private final String message;

    public LogoutResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
