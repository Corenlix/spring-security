package ru.denisqaa.learning.security.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.denisqaa.learning.security.springsecurity.dto.RoleDTO;
import ru.denisqaa.learning.security.springsecurity.dto.UserDTO;
import ru.denisqaa.learning.security.springsecurity.model.Role;
import ru.denisqaa.learning.security.springsecurity.service.RoleService;
import ru.denisqaa.learning.security.springsecurity.service.UserService;

import java.util.List;

import static java.time.LocalDate.now;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/")
    public String printText(ModelMap model) {
        List<String> phrases = List.of("Hello.", "Welcome to first web app. ", String.format("It's %s", now()));
        model.addAttribute("messages", phrases);
        return "index";
    }
}
