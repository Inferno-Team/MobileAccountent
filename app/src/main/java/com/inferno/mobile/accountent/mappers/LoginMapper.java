package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.LoginResponse;
import com.inferno.mobile.accountent.network.responses.LoginResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

public class LoginMapper implements DomainMapper<LoginResponseDto, LoginResponse> {
    @Override
    public LoginResponse mapToDomain(LoginResponseDto model) {
        return new LoginResponse(model.getToken(), model.getMessage(), model.getType());
    }

    @Override
    public LoginResponseDto mapFromDomain(LoginResponse loginResponse) {
        return new LoginResponseDto(loginResponse.getToken(),
                loginResponse.getMessage(), loginResponse.getToken());
    }
}
