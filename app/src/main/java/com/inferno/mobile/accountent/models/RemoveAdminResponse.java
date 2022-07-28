package com.inferno.mobile.accountent.models;

public class RemoveAdminResponse {

    private final int code;
    private final String message;
    private final User admin;

    public RemoveAdminResponse(int code, String message, User admin) {
        this.code = code;
        this.message = message;
        this.admin = admin;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public User getAdmin() {
        return admin;
    }
}
