package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Map;

public interface ItemService {
    Item createItem(Item item);

    List<Item> getAllItemsForOwner(Long ownerId);

    Item getItemById(Long id);

    Item updateItem(Long id, Map<String, String> updatedParams, Long ownerId);

    List<Item> searchItems(String text);
}
