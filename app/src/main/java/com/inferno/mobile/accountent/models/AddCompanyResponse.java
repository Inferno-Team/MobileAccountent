package com.inferno.mobile.accountent.models;

public class AddCompanyResponse {
    private final int code;
    private final String msg;
    private final Company company;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Company getCompany() {
        return company;
    }

    public AddCompanyResponse(int code, String msg, Company company) {
        this.code = code;
        this.msg = msg;
        this.company = company;
    }
}
