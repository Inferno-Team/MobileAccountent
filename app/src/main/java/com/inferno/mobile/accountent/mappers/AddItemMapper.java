package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.AddItemResponse;
import com.inferno.mobile.accountent.network.responses.AddItemResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

public class AddItemMapper implements DomainMapper<AddItemResponseDto, AddItemResponse> {
    @Override
    public AddItemResponse mapToDomain(AddItemResponseDto model) {
        return new AddItemResponse(model.getCode(), model.getMessage());
    }

    @Override
    public AddItemResponseDto mapFromDomain(AddItemResponse model) {
        return new AddItemResponseDto(model.getCode(), model.getMessage());
    }
}
