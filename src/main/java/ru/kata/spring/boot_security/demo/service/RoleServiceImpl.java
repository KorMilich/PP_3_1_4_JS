package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;


    public RoleServiceImpl(RoleRepository roleDao) {
        this.roleRepository = roleDao;
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Transactional
    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public boolean exist(String role) {
        return roleRepository.exist(role);
    }
}
