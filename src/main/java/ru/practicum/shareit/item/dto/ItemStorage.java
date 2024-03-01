package ru.practicum.shareit.item.dto;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemStorage {
    Item createItem(Item item);

    List<Item> getAllItemsForOwner(Long ownerId);

    Optional<Item> getItemById(Long id);

    Item updateItem(Long id, Map<String, String> updatedParams);

    List<Item> searchItems(String text);
}
