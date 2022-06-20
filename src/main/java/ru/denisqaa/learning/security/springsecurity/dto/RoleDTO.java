package ru.denisqaa.learning.security.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDTO {
    private int id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
