package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.AddCompanyResponse;
import com.inferno.mobile.accountent.network.responses.AddCompanyResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

public class AddCompanyMapper implements DomainMapper<AddCompanyResponseDto, AddCompanyResponse> {
    private final CompanyMapper companyMapper;

    public AddCompanyMapper(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    @Override
    public AddCompanyResponse mapToDomain(AddCompanyResponseDto model) {
        return new AddCompanyResponse(model.getCode(), model.getMsg(),
                companyMapper.mapToDomain(model.getCompany()));
    }

    @Override
    public AddCompanyResponseDto mapFromDomain(AddCompanyResponse addCompanyResponse) {
        return null;
    }
}
