package ru.practicum.shareit.user.dto;

import ru.practicum.shareit.user.User;

import java.util.List;
import java.util.Map;

public interface UserStorage {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, Map<String, String> updatedParams);

    void deleteUser(Long id);
}
