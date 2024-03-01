package ru.practicum.shareit.user.dto;

import ru.practicum.shareit.user.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserStorage {
    Optional<User> createUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> updateUser(Long id, Map<String, String> updatedParams);

    void deleteUser(Long id);
}
