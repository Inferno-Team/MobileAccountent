package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.BillItem;
import com.inferno.mobile.accountent.network.responses.BillItemDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import java.util.ArrayList;

public class BillItemMapper implements DomainMapper<BillItemDto, BillItem> {
    private final CategoryMapper categoryMapper;

    public BillItemMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public BillItem mapToDomain(BillItemDto model) {
        if (model == null)
            return null;
        return new BillItem(model.getCount(), categoryMapper.mapToDomain(model.getCategory()));
    }

    @Override
    public BillItemDto mapFromDomain(BillItem billItem) {
        return null;
    }

    public ArrayList<BillItem> toDomainList(ArrayList<BillItemDto> dtos) {
        ArrayList<BillItem> items = new ArrayList<>();
        for (BillItemDto dto : dtos)
            items.add(mapToDomain(dto));
        return items;
    }
}
