package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleRepository {
    Role findByRole (String role);
    void save (Role role);
    boolean exist(String role);

}
