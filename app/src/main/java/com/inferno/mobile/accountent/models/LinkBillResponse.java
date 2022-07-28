package com.inferno.mobile.accountent.models;


public class LinkBillResponse {

    private final int code;
    private final String message;
    private final Bill bill;

    public LinkBillResponse(int code, String message, Bill bill) {
        this.code = code;
        this.message = message;
        this.bill = bill;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Bill getBill() {
        return bill;
    }
}
