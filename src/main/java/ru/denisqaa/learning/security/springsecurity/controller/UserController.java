package ru.denisqaa.learning.security.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.denisqaa.learning.security.springsecurity.dto.UserDTO;
import ru.denisqaa.learning.security.springsecurity.service.impl.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String index(Principal principal, Model model) {
        model.addAttribute("user", userService.getByName(principal.getName()));
        return "user/index";
    }
}
