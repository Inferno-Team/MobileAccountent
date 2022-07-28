package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.Shop;
import com.inferno.mobile.accountent.network.responses.ShopDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import java.util.ArrayList;

import javax.inject.Inject;

public class ShopMapper implements DomainMapper<ShopDto, Shop> {

    private final UserMapper userMapper;
    private final WorkerMapper workerMapper;

    @Inject
    public ShopMapper(UserMapper userMapper, WorkerMapper workerMapper) {
        this.userMapper = userMapper;
        this.workerMapper = workerMapper;
    }

    @Override
    public Shop mapToDomain(ShopDto model) {
        return new Shop(
                model.getId(), model.getOwnerId(),
                model.getAdminId(),
                model.getShopeId(),
                model.isHasManager(),
                model.getName(),
                model.getLocation(),
                model.isApproved(),
                model.getRequestType(),
                model.getCreated_at(),
                model.getUpdated_at(),
                model.getAdmin() != null ? userMapper.mapToDomain(model.getAdmin()) : null,
                model.getOwner() != null ? userMapper.mapToDomain(model.getOwner()) : null,
                workerMapper.toDomainList(model.getWorkers())

        );
    }

    @Override
    public ShopDto mapFromDomain(Shop model) {
        return new ShopDto(
                model.getId(), model.getOwnerId(),
                model.getAdminId(),
                model.getShopeId(),
                model.getShopName(),
                model.getLocation(),
                model.isApproved(),
                model.isHasManager(),
                model.getRequestType(),
                model.getCreated_at(),
                model.getUpdated_at(),
                userMapper.mapFromDomain(model.getAdmin()),
                userMapper.mapFromDomain(model.getOwner()),
                workerMapper.fromDomainList(model.getWorkers())
        );
    }

    public ArrayList<Shop> mapToDomainList(ArrayList<ShopDto> dtos) {
        ArrayList<Shop> shops = new ArrayList<>();
        for (ShopDto dto : dtos)
            shops.add(mapToDomain(dto));
        return shops;
    }
}
