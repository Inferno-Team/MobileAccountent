package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.RemoveAdminResponse;
import com.inferno.mobile.accountent.network.responses.RemoveAdminResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import javax.inject.Inject;

public class RemoveAdminMapper implements DomainMapper<RemoveAdminResponseDto, RemoveAdminResponse> {
    private final UserMapper mapper;

    @Inject
    public RemoveAdminMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public RemoveAdminResponse mapToDomain(RemoveAdminResponseDto model) {
        return new RemoveAdminResponse(model.getCode(), model.getMessage(),
                mapper.mapToDomain(model.getAdmin()));
    }

    @Override
    public RemoveAdminResponseDto mapFromDomain(RemoveAdminResponse model) {
        return new RemoveAdminResponseDto(model.getCode(), model.getMessage(),
                mapper.mapFromDomain(model.getAdmin()));
    }
}
