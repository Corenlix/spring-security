package ru.denisqaa.learning.security.springsecurity.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.denisqaa.learning.security.springsecurity.dto.RoleDTO;
import ru.denisqaa.learning.security.springsecurity.service.RoleService;

@Component
@RequiredArgsConstructor
public class RoleConverter implements Converter<String, RoleDTO> {
    private final RoleService roleService;

    @Override
    public RoleDTO convert(String id) {
        return roleService.getById(Integer.parseInt(id));
    }
}
