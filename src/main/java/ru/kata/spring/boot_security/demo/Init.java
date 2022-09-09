package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class Init {
    private UserService userService;
    private RoleService roleService;


    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {

        Role adminRole = new Role("ROLE_ADMIN");
        roleService.save(adminRole);


        Role userRole = new Role("ROLE_USER");
        roleService.save(userRole);


        List<Role> adminRolesList = new ArrayList<>();
        adminRolesList.add(roleService.findByRole("ROLE_ADMIN"));
        adminRolesList.add(roleService.findByRole("ROLE_USER"));
        User admin = new User("admin", "admin", 42, "admin@mail.ru",
                "admin", adminRolesList);
        userService.save(admin);


        List<Role> userRolesList = new ArrayList<>();
        userRolesList.add(roleService.findByRole("ROLE_USER"));
        User user = new User("user", "user", 42, "user@mail.ru",
                "user", userRolesList);
        userService.save(user);

    }
}
