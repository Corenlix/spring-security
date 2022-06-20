package ru.denisqaa.learning.security.springsecurity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.denisqaa.learning.security.springsecurity.dto.RoleDTO;
import ru.denisqaa.learning.security.springsecurity.mappers.RoleMapper;
import ru.denisqaa.learning.security.springsecurity.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService implements ru.denisqaa.learning.security.springsecurity.service.RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<RoleDTO> getAll() {
        return RoleMapper.INSTANCE.toDTO(roleRepository.findAll());
    }

    @Override
    public RoleDTO getById(int id) {
        return RoleMapper.INSTANCE.toDTO(roleRepository.findById(id).orElse(null));
    }
}
