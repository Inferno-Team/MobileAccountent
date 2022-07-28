package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.Bill;
import com.inferno.mobile.accountent.network.responses.BillDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import javax.inject.Inject;

public class BillMapper implements DomainMapper<BillDto, Bill> {


    private final BillItemMapper mapper;
    private final ShopMapper shopMapper;

    public BillMapper(BillItemMapper mapper, ShopMapper shopMapper) {
        this.mapper = mapper;
        this.shopMapper = shopMapper;
    }

    @Override
    public Bill mapToDomain(BillDto model) {
        if (model == null)
            return null;
        return new Bill(model.getCheck(), shopMapper.mapToDomain(model.getShop()),
                mapper.toDomainList(model.getItems()));
    }

    @Override
    public BillDto mapFromDomain(Bill billDto) {
        return null;
    }
}
