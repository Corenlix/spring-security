package ru.denisqaa.learning.security.springsecurity.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.denisqaa.learning.security.springsecurity.dto.UserDTO;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDTO> getUsers();

    UserDTO getById(int id);

    UserDTO getByName(String name);

    void saveUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void delete(int id);
}
