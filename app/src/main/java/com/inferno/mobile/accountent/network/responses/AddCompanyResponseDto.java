package com.inferno.mobile.accountent.network.responses;

import com.google.gson.annotations.SerializedName;

public class AddCompanyResponseDto {
    @SerializedName("code")
    private final int code;
    @SerializedName("msg")
    private final String msg;
    @SerializedName("comp")
    private final CompanyDto company;

    public AddCompanyResponseDto(int code, String msg, CompanyDto company) {
        this.code = code;
        this.msg = msg;
        this.company = company;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public CompanyDto getCompany() {
        return company;
    }
}
