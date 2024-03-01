package ru.practicum.shareit.user.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.AlreadyExistException;
import ru.practicum.shareit.user.User;

import java.util.*;

import java.util.Optional;

@Slf4j
@Repository
public class UserRepository implements UserStorage {
    private final Map<Long, User> users = new HashMap<>();
    private Long id = 0L;

    @Override
    public Optional<User> createUser(User user) {
        if (getAllEmails().contains(user.getEmail())) {
            log.error("Пользователь с email = {} уже существует", user.getEmail());
            throw new AlreadyExistException(String.format("Пользователь с email = %s уже существует", user.getEmail()));
        }
        user.setId(++id);
        users.put(user.getId(), user);
        return Optional.of(user);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> getUserById(Long id) {
        if (!users.containsKey(id)) {
            log.error("Пользователь с id = {} не найден", id);
            return Optional.empty();
        }
        return Optional.of(users.get(id));
    }

    @Override
    public Optional<User> updateUser(Long id, Map<String, String> updatedParams) {
        if (!users.containsKey(id)) {
            log.error("Пользователь с id = {} не найден", id);
            return Optional.empty();
        }
        User user = users.get(id);
        for (String key : updatedParams.keySet()) {
            switch (key) {
                case "email":
                    String email = updatedParams.get("email");
                    if (getAllEmails().contains(email) && !user.getEmail().equals(email)) {
                        log.error("Другой пользователь с email = {} уже существует", user.getEmail());
                        throw new AlreadyExistException(String.format("Другой пользователь с email = %s уже существует", user.getEmail()));
                    }
                    user.setEmail(email);
                    break;
                case "name":
                    user.setName(updatedParams.get("name"));
                    break;
            }
        }
        return Optional.of(user);
    }

    @Override
    public void deleteUser(Long id) {
        users.remove(id);
    }

    private Set<String> getAllEmails() {
        Set<String> emails = new HashSet<>();
        for (User user : users.values()) {
            emails.add(user.getEmail());
        }
        return emails;
    }
}
