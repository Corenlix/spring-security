package ru.denisqaa.learning.security.springsecurity.service;

import ru.denisqaa.learning.security.springsecurity.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAll();

    RoleDTO getById(int id);
}
