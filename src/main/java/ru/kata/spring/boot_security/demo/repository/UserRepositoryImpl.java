package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository

public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
//    @EntityGraph(attributePaths = { "roles"})
    public User findByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = : e");
        query.setParameter("e", email);
        return (User) query.getSingleResult();
    }

    @Override
//    @EntityGraph(attributePaths = { "roles"})
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public boolean exist(String email) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = : e");
        query.setParameter("e", email);
        if (((org.hibernate.query.Query) query).list().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<User> tryGetUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u join fetch u.roles r where u.firstName = :username", User.class);
        query.setParameter("username", username);
        try {
            Optional<User> rev = Optional.ofNullable(query.getSingleResult());
            return rev;
        } catch (Exception e) {
            return Optional.empty();

        }
    }
}

