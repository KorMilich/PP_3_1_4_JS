package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
//        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Transactional
    @Override
    public void save(User user) {
        String pass = user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public void update(User user) {
        String pass = user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));
        userRepository.update(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public boolean exist(String email) {
        return userRepository.exist(email);
    }

}
