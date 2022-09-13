package ru.kata.spring.boot_security.demo.repository;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User findByEmail(String email);
    List<User> getUsers();
    void save(User user);
    void update(User user);
    void delete(int id);
    boolean exist(String email);

    Optional<User> tryGetUserByUsername(String username);
}
