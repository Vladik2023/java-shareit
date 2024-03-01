package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserStorage;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userStorage;

    public User createUser(User user) {
        Optional<User> optionalUser = userStorage.createUser(user);
        return optionalUser.orElseThrow(() -> new RuntimeException("Failed to create user"));
    }

    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userStorage.getUserById(id);
        return optionalUser.orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User updateUser(Long id, Map<String, String> userUpdatedParams) {
        Optional<User> optionalUser = userStorage.updateUser(id, userUpdatedParams);
        return optionalUser.orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void deleteUser(Long id) {
        userStorage.deleteUser(id);
    }
}
