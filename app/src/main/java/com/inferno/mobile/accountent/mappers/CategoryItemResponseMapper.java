package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.CategoryItemResponse;
import com.inferno.mobile.accountent.network.responses.CategoryItemResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import javax.inject.Inject;

public class CategoryItemResponseMapper implements
        DomainMapper<CategoryItemResponseDto, CategoryItemResponse> {
    private final CategoryMapper categoryMapper;

    @Inject
    public CategoryItemResponseMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryItemResponse mapToDomain(CategoryItemResponseDto model) {
        return new CategoryItemResponse(model.getMsg()
                , categoryMapper.mapToDomain(model.getCategory())
                , model.getCode());
    }

    @Override
    public CategoryItemResponseDto mapFromDomain(CategoryItemResponse model) {
        return new CategoryItemResponseDto(model.getMsg()
                , categoryMapper.mapFromDomain(model.getCategory())
                , model.getCode());
    }
}
