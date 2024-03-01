package ru.practicum.shareit.item.dto;

import ru.practicum.shareit.item.model.Item;

public class ItemMapperImpl {
    public static ItemDto convertToDto(Item item) {
        return ItemMapper.INSTANCE.convertToDto(item);
    }

    public static Item convertToEntity(ItemDto itemDto, Long ownerId) {
        return ItemMapper.INSTANCE.convertToEntity(itemDto, ownerId);
    }
}
