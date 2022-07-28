package com.inferno.mobile.accountent.mappers;

import com.inferno.mobile.accountent.models.User;
import com.inferno.mobile.accountent.models.UserType;
import com.inferno.mobile.accountent.network.responses.UserDto;
import com.inferno.mobile.accountent.utils.DomainMapper;

import java.util.ArrayList;

public class UserMapper implements DomainMapper<UserDto, User> {
    @Override
    public User mapToDomain(UserDto model) {
        if (model == null)return null;
        return new User(model.getId(), model.getUserName(), model.getEmail(), model.getPhone(),
                UserType.valueOf(model.getType()));
    }

    @Override
    public UserDto mapFromDomain(User user) {
        return new UserDto(user.getId(), user.getUserName(), user.getEmail(),
                user.getPhone(), user.getType().name());
    }

    public ArrayList<User> mapToDomainList(ArrayList<UserDto> usersDto) {
        ArrayList<User> users = new ArrayList<>();
        for (UserDto user : usersDto)
            users.add(mapToDomain(user));
        return users;
    }
}
