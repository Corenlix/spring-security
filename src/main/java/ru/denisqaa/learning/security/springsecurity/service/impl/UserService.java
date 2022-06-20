package ru.denisqaa.learning.security.springsecurity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.denisqaa.learning.security.springsecurity.dto.UserDTO;
import ru.denisqaa.learning.security.springsecurity.mappers.UserMapper;
import ru.denisqaa.learning.security.springsecurity.model.User;
import ru.denisqaa.learning.security.springsecurity.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements ru.denisqaa.learning.security.springsecurity.service.UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getUsers() {
        return UserMapper.INSTANCE.toDTO(userRepository.findAll());
    }

    @Override
    public UserDTO getById(int id) {
        return UserMapper.INSTANCE.toDTO(userRepository.findById(id).orElse(null));
    }

    public UserDTO getByName(String name) {
        return UserMapper.INSTANCE.toDTO(userRepository.findByName(name).orElse(null));
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        if (!userRepository.existsByName(userDTO.getName())) {
            User user = UserMapper.INSTANCE.toUser(userDTO);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toUser(userDTO);
        Optional<User> existUser = userRepository.findById(userDTO.getId());
        if (existUser.isPresent()) {
            if (!Objects.equals(existUser.get().getPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userRepository.save(user);
        }
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User doesn't exist");
        }
        else {
            return UserMapper.INSTANCE.toUser(user);
        }
    }
}
