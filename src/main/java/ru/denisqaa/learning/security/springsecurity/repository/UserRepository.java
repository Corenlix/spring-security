package ru.denisqaa.learning.security.springsecurity.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.denisqaa.learning.security.springsecurity.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name);
    boolean existsByName(String name);
}
