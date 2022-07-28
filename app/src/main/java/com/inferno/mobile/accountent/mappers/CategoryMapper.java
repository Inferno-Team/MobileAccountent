package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.Category;
import com.inferno.mobile.accountent.network.responses.CategoryDto;
import com.inferno.mobile.accountent.network.responses.CompanyDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import java.util.ArrayList;

public class CategoryMapper implements DomainMapper<CategoryDto, Category> {
    private final CompanyMapper companyMapper;

    public CategoryMapper(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    @Override
    public Category mapToDomain(CategoryDto model) {
        if (model == null)
            return null;
        return new Category(model.getId(), model.getName(), model.getCount(),
                model.getPrice(), companyMapper.mapToDomain(model.getCompany()),
                model.getBarcode());
    }

    @Override
    public CategoryDto mapFromDomain(Category category) {
        return null;
    }

    public ArrayList<Category> toDomainList(ArrayList<CategoryDto> dtos) {
        ArrayList<Category> categories = new ArrayList<>();
        for (CategoryDto dto : dtos)
            categories.add(mapToDomain(dto));
        return categories;
    }
}
