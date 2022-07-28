package com.inferno.mobile.accountent.models;

import com.inferno.mobile.accountent.network.responses.UserDto;

public class AddManagerResponse {
    private final int code;
    private final String message;
    private final User user;

    public AddManagerResponse(int code, String message, User user) {
        this.code = code;
        this.message = message;
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
