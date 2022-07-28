package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.LinkBillResponse;
import com.inferno.mobile.accountent.network.responses.LinkBillResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import javax.inject.Inject;

public class LinkBillResponseMapper implements DomainMapper<LinkBillResponseDto, LinkBillResponse> {
    private final BillMapper mapper;

    public LinkBillResponseMapper(BillMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public LinkBillResponse mapToDomain(LinkBillResponseDto model) {
        if (model == null)
            return null;
        return new LinkBillResponse(model.getCode(), model.getMessage(),
                mapper.mapToDomain(model.getBillDto()));
    }

    @Override
    public LinkBillResponseDto mapFromDomain(LinkBillResponse linkBillResponse) {
        return null;
    }
}
