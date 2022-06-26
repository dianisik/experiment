package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.MyUserDetailsService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final MyUserDetailsService myUserDetailsService;

    @GetMapping("/info")
    public String goHome(Principal principal, Model model){
        User user = myUserDetailsService.findByUserName(principal.getName());
        model.addAttribute("activeUser", user);

        return "admin_info";
    }
}
