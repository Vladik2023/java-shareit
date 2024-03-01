package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;

import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.practicum.shareit.item.dto.ItemMapper.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private static final String USER_HEADER = "X-Sharer-User-Id";

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestHeader(USER_HEADER) Long ownerId, @Valid @RequestBody ItemDto itemDto) {
        Item item = convertToEntity(itemDto, ownerId);
        return ResponseEntity.ok().body(convertToDto(itemService.createItem(item))); // Возвращение ResponseEntity
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItemsForOwner(@RequestHeader(USER_HEADER) Long ownerId) {
        List<ItemDto> items = itemService.getAllItemsForOwner(ownerId).stream()
                .map(ItemMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(items); // Возвращение ResponseEntity
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok().body(convertToDto(itemService.getItemById(id))); // Возвращение ResponseEntity
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(@RequestHeader(USER_HEADER) Long ownerId, @PathVariable Long id, @RequestBody Map<String, String> formParams) {
        return ResponseEntity.ok().body(convertToDto(itemService.updateItem(id, formParams, ownerId))); // Возвращение ResponseEntity
    }

    @GetMapping("/search")
    private ResponseEntity<List<ItemDto>> searchItems(@RequestParam String text) {
        List<ItemDto> items = itemService.searchItems(text).stream()
                .map(ItemMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(items); // Возвращение ResponseEntity
    }
}
