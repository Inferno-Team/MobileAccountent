package com.inferno.mobile.accountent.models;

public class AddAdminResponse {
    private final int statusCode;
    private final String message;
    private final User admin;

    public AddAdminResponse(int statusCode, String message, User admin) {
        this.statusCode = statusCode;
        this.message = message;
        this.admin = admin;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public User getAdmin() {
        return admin;
    }
}
