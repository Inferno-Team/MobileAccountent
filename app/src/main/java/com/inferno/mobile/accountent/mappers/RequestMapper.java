package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.ShopRequestResponse;
import com.inferno.mobile.accountent.network.responses.ShopRequestResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import javax.inject.Inject;

public class RequestMapper implements DomainMapper<ShopRequestResponseDto, ShopRequestResponse> {

    private final  ShopMapper shopMapper;

    @Inject
    public RequestMapper(ShopMapper shopMapper) {
        this.shopMapper = shopMapper;
    }

    @Override
    public ShopRequestResponse mapToDomain(ShopRequestResponseDto model) {
        return new ShopRequestResponse(model.getCode(),
                shopMapper.mapToDomainList(model.getShops()));
    }

    @Override
    public ShopRequestResponseDto mapFromDomain(ShopRequestResponse shopRequestResponse) {
        return null;
    }
}
