package ru.denisqaa.learning.security.springsecurity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.denisqaa.learning.security.springsecurity.dao.RoleDao;
import ru.denisqaa.learning.security.springsecurity.dto.RoleDTO;
import ru.denisqaa.learning.security.springsecurity.mappers.RoleMapper;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService implements ru.denisqaa.learning.security.springsecurity.service.RoleService {
    private final RoleDao roleDao;

    @Override
    public void save(RoleDTO role) {
        roleDao.save(RoleMapper.INSTANCE.toRole(role));
    }

    @Override
    public RoleDTO getByName(String name) {
        return RoleMapper.INSTANCE.toDTO(roleDao.getByName(name));
    }

    @Override
    public RoleDTO getById(int id) {
        return RoleMapper.INSTANCE.toDTO(roleDao.getById(id));
    }

    @Override
    public List<RoleDTO> getAll() {
        return RoleMapper.INSTANCE.toDTO(roleDao.getAll());
    }
}
