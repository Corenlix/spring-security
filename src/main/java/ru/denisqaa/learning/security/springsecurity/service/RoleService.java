package ru.denisqaa.learning.security.springsecurity.service;

import ru.denisqaa.learning.security.springsecurity.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    void save(RoleDTO role);
    RoleDTO getByName(String name);
    RoleDTO getById(int id);
    List<RoleDTO> getAll();
}
