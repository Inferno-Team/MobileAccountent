package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.MessageResponse;
import com.inferno.mobile.accountent.network.responses.MessageResponseDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

public class MessageMapper implements DomainMapper<MessageResponseDto, MessageResponse> {
    @Override
    public MessageResponse mapToDomain(MessageResponseDto model) {
        return new MessageResponse(model.getCode(), model.getMessage(), model.getData());
    }

    @Override
    public MessageResponseDto mapFromDomain(MessageResponse model) {
        return new MessageResponseDto(model.getCode(), model.getMessage(), model.getData());
    }
}
