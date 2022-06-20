package ru.denisqaa.learning.security.springsecurity.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.denisqaa.learning.security.springsecurity.dto.UserDTO;
import ru.denisqaa.learning.security.springsecurity.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toUser(UserDTO dto);

    List<UserDTO> toDTO(List<User> userList);
}
