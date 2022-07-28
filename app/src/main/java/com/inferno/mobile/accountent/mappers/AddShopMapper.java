package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.AddShopRequestResponse;
import com.inferno.mobile.accountent.network.responses.AddShopRequestResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import javax.inject.Inject;

public class AddShopMapper implements DomainMapper<AddShopRequestResponseDto,
        AddShopRequestResponse> {
    private ShopMapper shopMapper;
    @Inject
    public AddShopMapper(ShopMapper shopMapper) {
        this.shopMapper = shopMapper;
    }

    @Override
    public AddShopRequestResponse mapToDomain(AddShopRequestResponseDto model) {
        return new AddShopRequestResponse(model.getCode(),model.getMessage(),
                shopMapper.mapToDomain(model.getShop()));
    }

    @Override
    public AddShopRequestResponseDto mapFromDomain(AddShopRequestResponse model) {
        return new AddShopRequestResponseDto(model.getCode(), model.getMsg(),
                shopMapper.mapFromDomain(model.getShop()));
    }
}
