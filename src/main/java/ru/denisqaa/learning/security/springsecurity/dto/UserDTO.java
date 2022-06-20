package ru.denisqaa.learning.security.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.denisqaa.learning.security.springsecurity.model.Role;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private int id;
    private String name;
    private String password;
    private Collection<RoleDTO> roles;
}
