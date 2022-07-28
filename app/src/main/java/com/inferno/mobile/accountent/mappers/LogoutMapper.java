package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.LogoutResponse;
import com.inferno.mobile.accountent.network.responses.LogoutResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

public class LogoutMapper implements DomainMapper<LogoutResponseDto, LogoutResponse> {
    @Override
    public LogoutResponse mapToDomain(LogoutResponseDto model) {
        return new LogoutResponse(model.getStatusCode(), model.getMessage());
    }

    @Override
    public LogoutResponseDto mapFromDomain(LogoutResponse model) {
        return new LogoutResponseDto(model.getStatusCode(), model.getMessage());

    }
}
