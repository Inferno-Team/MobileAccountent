package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.Company;
import com.inferno.mobile.accountent.network.responses.CompanyDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import java.util.ArrayList;

public class CompanyMapper implements DomainMapper<CompanyDto, Company> {
    @Override
    public Company mapToDomain(CompanyDto model) {
        if (model == null)
            return null;
        return new Company(model.getId(), model.getName(), model.getCatCount());
    }

    @Override
    public CompanyDto mapFromDomain(Company company) {
        return new CompanyDto(company.getId(), company.getName(), company.getCatCount());
    }

    public ArrayList<Company> mapToDomainList(ArrayList<CompanyDto> dtos) {
        ArrayList<Company> companies = new ArrayList<>();
        for (CompanyDto dto : dtos)
            companies.add(mapToDomain(dto));
        return companies;
    }
}
