package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.Response2Request;
import com.inferno.mobile.accountent.network.responses.Response2RequestDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import javax.inject.Inject;

public class Response2RequestMapper implements DomainMapper<Response2RequestDto, Response2Request> {

    private final ShopMapper mapper;

    @Inject
    public Response2RequestMapper(ShopMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Response2Request mapToDomain(Response2RequestDto model) {
        return new Response2Request(model.getCode(), model.getMessage(),
                mapper.mapToDomain(model.getShop()));
    }

    @Override
    public Response2RequestDto mapFromDomain(Response2Request response2Request) {
        return null;
    }
}
