package ru.denisqaa.learning.security.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.denisqaa.learning.security.springsecurity.dto.UserDTO;
import ru.denisqaa.learning.security.springsecurity.service.RoleService;
import ru.denisqaa.learning.security.springsecurity.service.impl.UserService;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String index(@ModelAttribute("user") UserDTO userDTO, Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("allRoles", roleService.getAll());
        return "admin/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") UserDTO userDTO, Model model) {
        model.addAttribute("allRoles", roleService.getAll());
        return "admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("allRoles", roleService.getAll());
        return "admin/edit";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("user") UserDTO userDTO, @PathVariable("id") int id) {
        userDTO.setId(id);
        userService.updateUser(userDTO);
            return "redirect:/";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserDTO show(@PathVariable("id") int id, Model model) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }
}
