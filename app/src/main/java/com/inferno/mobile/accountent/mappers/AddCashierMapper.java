package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.AddCashierResponse;
import com.inferno.mobile.accountent.network.responses.AddCashierResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

public class AddCashierMapper implements DomainMapper<AddCashierResponseDto, AddCashierResponse> {
    @Override
    public AddCashierResponse mapToDomain(AddCashierResponseDto model) {
        return new AddCashierResponse(model.getCode(), model.getMessage());
    }

    @Override
    public AddCashierResponseDto mapFromDomain(AddCashierResponse addCashierResponse) {
        return null;
    }
}
