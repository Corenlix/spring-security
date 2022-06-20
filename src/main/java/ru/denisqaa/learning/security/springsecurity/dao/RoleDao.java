package ru.denisqaa.learning.security.springsecurity.dao;

import ru.denisqaa.learning.security.springsecurity.model.Role;

import java.util.List;

public interface RoleDao {
    void save(Role role);
    Role getByName(String name);
    Role getById(int id);
    List<Role> getAll();
}
