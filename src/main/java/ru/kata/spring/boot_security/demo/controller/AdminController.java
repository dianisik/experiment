package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/login")
    public String login() {return "/login";}

    @GetMapping("/admin")
    public String userList(Model model) {return "/admin";}
}