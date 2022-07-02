package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminRestController {
    private final UserService userService;

    @GetMapping("/api/user")
    public User getUserByUsername(Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        return user;
    }

    @GetMapping("/api/admin")
    public List<User> userList() {
        return userService.getAllUsers();
    }

    @PostMapping("/api/admin")
    public List<User> addUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return userService.getAllUsers();
    }

    @PutMapping("/api/admin")
    public User updateUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }
}