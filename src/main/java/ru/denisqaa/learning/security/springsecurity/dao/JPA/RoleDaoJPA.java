package ru.denisqaa.learning.security.springsecurity.dao.JPA;

import org.springframework.stereotype.Repository;
import ru.denisqaa.learning.security.springsecurity.dao.RoleDao;
import ru.denisqaa.learning.security.springsecurity.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoJPA implements RoleDao {
    public static final String GET_BY_NAME_HQL = "select r from Role r where r.name = :name";
    private static final String GET_ALL_HQL = String.format("From %s", Role.class.getSimpleName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getByName(String name) {
        Query query = entityManager.createQuery(GET_BY_NAME_HQL);
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }

    @Override
    public Role getById(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> getAll() {
        return entityManager.createQuery(GET_ALL_HQL).getResultList();
    }
}
