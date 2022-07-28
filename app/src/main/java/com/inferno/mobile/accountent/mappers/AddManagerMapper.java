package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.AddManagerResponse;
import com.inferno.mobile.accountent.network.responses.AddManagerResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import javax.inject.Inject;

public class AddManagerMapper implements DomainMapper<AddManagerResponseDto, AddManagerResponse> {

    private final UserMapper userMapper;

    @Inject
    public AddManagerMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public AddManagerResponse mapToDomain(AddManagerResponseDto model) {
        return new AddManagerResponse(model.getCode(), model.getMessage(),
                userMapper.mapToDomain(model.getUser()));
    }

    @Override
    public AddManagerResponseDto mapFromDomain(AddManagerResponse addManagerResponse) {
        return null;
    }
}
