package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findByRole(String role) {
        Query query = entityManager.createQuery("SELECT r FROM Role r WHERE r.role = : role");
        query.setParameter("role", role);
        return (Role) query.getSingleResult();
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public boolean exist(String role) {
        Query query = entityManager.createQuery("SELECT r FROM Role r WHERE r.role = : role");
        query.setParameter("role", role);
        if (((org.hibernate.query.Query)query).list().isEmpty()) {
            return false;
        }
        return true;
    }
}
