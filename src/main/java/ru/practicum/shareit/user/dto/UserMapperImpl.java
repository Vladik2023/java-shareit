package ru.practicum.shareit.user.dto;

import ru.practicum.shareit.user.User;

public class UserMapperImpl {
    public static UserDto convertToDto(User user) {
        return UserMapper.INSTANCE.convertToDto(user);
    }

    public static User convertToEntity(UserDto userDto) {
        return UserMapper.INSTANCE.convertToEntity(userDto);
    }
}

