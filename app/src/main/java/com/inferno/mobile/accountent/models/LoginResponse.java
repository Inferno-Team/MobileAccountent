package com.inferno.mobile.accountent.models;

public class LoginResponse {
    private final String token;
    private final String message;
    private final String type;


    public LoginResponse(String token, String message, String type) {
        this.token = token;
        this.message = message;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
