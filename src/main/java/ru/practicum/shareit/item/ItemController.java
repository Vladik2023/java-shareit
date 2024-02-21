package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapperImpl;
import ru.practicum.shareit.item.model.Item;

import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.practicum.shareit.item.dto.ItemMapperImpl.convertToDto;
import static ru.practicum.shareit.item.dto.ItemMapperImpl.convertToEntity;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private static final String USER_HEADER = "X-Sharer-User-Id";

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestHeader(USER_HEADER) Long ownerId, @Valid @RequestBody ItemDto itemDto) {
        Item item = convertToEntity(itemDto, ownerId);
        Item createdItem = itemService.createItem(item);
        ItemDto createdItemDto = convertToDto(createdItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItemDto);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItemsForOwner(@RequestHeader(USER_HEADER) Long ownerId) {
        List<Item> items = itemService.getAllItemsForOwner(ownerId);
        List<ItemDto> itemDtos = items.stream()
                .map(ItemMapperImpl::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(itemDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        ItemDto itemDto = convertToDto(item);
        return ResponseEntity.ok().body(itemDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(@RequestHeader(USER_HEADER) Long ownerId, @PathVariable Long id, @RequestBody Map<String, String> formParams) {
        Item updatedItem = itemService.updateItem(id, formParams, ownerId);
        ItemDto updatedItemDto = convertToDto(updatedItem);
        return ResponseEntity.ok().body(updatedItemDto);
    }

    @GetMapping("/search")
    private ResponseEntity<List<ItemDto>> searchItems(@RequestParam String text) {
        List<Item> items = itemService.searchItems(text);
        List<ItemDto> itemDtos = items.stream()
                .map(ItemMapperImpl::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(itemDtos);
    }
}
