package ru.practicum.shareit.item.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.practicum.shareit.item.model.Item;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mapping(target = "isAvailableToRent", source = "isAvailableToRent")
    ItemDto convertToDto(Item item);

    @Mapping(target = "isAvailableToRent", source = "isAvailableToRent")
    Item convertToEntity(ItemDto itemDto, Long ownerId);
}
