package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.BillDetails;
import com.inferno.mobile.accountent.network.responses.BillDetailsDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import java.util.ArrayList;

import javax.inject.Inject;

public class BillDetailsMapper implements DomainMapper<BillDetailsDto, BillDetails> {
    private final UserMapper userMapper;
    private final ShopMapper shopMapper;
    private final BillItemMapper itemMapper;

    @Inject
    public BillDetailsMapper(UserMapper userMapper,
                             ShopMapper shopMapper,
                             BillItemMapper itemMapper) {
        this.userMapper = userMapper;
        this.shopMapper = shopMapper;
        this.itemMapper = itemMapper;
    }

    @Override
    public BillDetails mapToDomain(BillDetailsDto model) {
        return new BillDetails(
                model.getId(),
                model.getCheck(),
                model.getCreatedAt(),
                userMapper.mapToDomain(model.getCashier()),
                shopMapper.mapToDomain(model.getShop()),
                itemMapper.toDomainList(model.getItems())
        );
    }

    @Override
    public BillDetailsDto mapFromDomain(BillDetails billDetails) {
        return null;
    }

    public ArrayList<BillDetails> toDomainList(ArrayList<BillDetailsDto> dtos) {
        ArrayList<BillDetails> list = new ArrayList<>();
        for (BillDetailsDto dto : dtos)
            list.add(mapToDomain(dto));
        return list;
    }
}
