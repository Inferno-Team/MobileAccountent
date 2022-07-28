package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.AddAdminResponse;
import com.inferno.mobile.accountent.network.responses.AddAdminResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import javax.inject.Inject;

public class AddAdminMapper implements DomainMapper<AddAdminResponseDto, AddAdminResponse> {

    private final UserMapper mapper;

    @Inject
    public AddAdminMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public AddAdminResponse mapToDomain(AddAdminResponseDto model) {
        return new AddAdminResponse(model.getStatusCode(), model.getMessage(),
                mapper.mapToDomain(model.getAdmin()));

    }

    @Override
    public AddAdminResponseDto mapFromDomain(AddAdminResponse model) {
        return new AddAdminResponseDto(model.getStatusCode(), model.getMessage(),
                mapper.mapFromDomain(model.getAdmin()));
    }
}
