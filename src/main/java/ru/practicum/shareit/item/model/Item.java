package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class Item {
    private Long id;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotEmpty(message = "description cannot be empty")
    private String description;
    private boolean isAvailableToRent;
    private Long ownerId;
}
