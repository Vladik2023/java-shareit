package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.practicum.shareit.user.dto.UserMapper.*;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        return ResponseEntity.ok().body(convertToDto(userService.createUser(user))); // Возвращение ResponseEntity
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers().stream()
                .map(UserMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(users); // Возвращение ResponseEntity
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(convertToDto(userService.getUserById(id))); // Возвращение ResponseEntity
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody Map<String, String> formParams) {
        return ResponseEntity.ok().body(convertToDto(userService.updateUser(id, formParams))); // Возвращение ResponseEntity
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // Возвращение ResponseEntity
    }
}
