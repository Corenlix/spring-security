package ru.denisqaa.learning.security.springsecurity.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.denisqaa.learning.security.springsecurity.dto.RoleDTO;
import ru.denisqaa.learning.security.springsecurity.model.Role;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDTO(Role role);

    Role toRole(RoleDTO dto);

    List<RoleDTO> toDTO(List<Role> rolesList);
}
