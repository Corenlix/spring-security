package ru.denisqaa.learning.security.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.denisqaa.learning.security.springsecurity.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
