package ru.practicum.shareit.user.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.practicum.shareit.user.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    UserDto convertToDto(User user);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    User convertToEntity(UserDto userDto);
}
