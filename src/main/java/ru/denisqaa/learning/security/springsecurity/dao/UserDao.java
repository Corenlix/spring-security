package ru.denisqaa.learning.security.springsecurity.dao;

import ru.denisqaa.learning.security.springsecurity.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsersByCount(int number);

    List<User> getUsers();

    User getById(int id);

    User getByName(String name);

    void saveUser(User user);

    void updateUser(User user, int id);

    void delete(int id);
}
