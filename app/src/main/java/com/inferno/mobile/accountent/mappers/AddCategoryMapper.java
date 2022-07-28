package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.AddCategoryResponse;
import com.inferno.mobile.accountent.network.responses.AddCategoryResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

public class AddCategoryMapper implements DomainMapper<AddCategoryResponseDto, AddCategoryResponse> {
    private final CategoryMapper categoryMapper;

    public AddCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public AddCategoryResponse mapToDomain(AddCategoryResponseDto model) {
        return new AddCategoryResponse(model.getCode(), model.getMessage(),
                categoryMapper.mapToDomain(model.getCategory()));
    }

    @Override
    public AddCategoryResponseDto mapFromDomain(AddCategoryResponse addCategoryResponse) {
        return null;
    }
}
