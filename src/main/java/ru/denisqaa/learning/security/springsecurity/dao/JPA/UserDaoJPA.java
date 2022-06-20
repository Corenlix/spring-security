package ru.denisqaa.learning.security.springsecurity.dao.JPA;

import org.springframework.stereotype.Repository;
import ru.denisqaa.learning.security.springsecurity.dao.UserDao;
import ru.denisqaa.learning.security.springsecurity.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoJPA implements UserDao {
    public static final String GET_BY_NAME_HQL = "select u from User u where u.name = :name";
    private static final String GET_ALL_HQL = String.format("From %s", User.class.getSimpleName());
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery(GET_ALL_HQL).getResultList();
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByName(String name) {
        Query query = entityManager.createQuery(GET_BY_NAME_HQL);
        query.setParameter("name", name);
        return (User) query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getUsersByCount(int count) {
        List<User> users = entityManager.createQuery(GET_ALL_HQL).setMaxResults(count).getResultList();
        return users;
    }

    @Override
    public void updateUser(User user, int id) {
        User existUser = entityManager.find(User.class, id);
        if (existUser == null) {
            return;
        }

        existUser.setId(user.getId());
        existUser.setName(user.getName());
        existUser.setPassword(user.getPassword());
        existUser.setRoles(user.getRoles());

        entityManager.merge(existUser);
    }

    @Override
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
