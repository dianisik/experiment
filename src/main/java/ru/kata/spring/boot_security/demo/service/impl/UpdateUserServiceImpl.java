package ru.kata.spring.boot_security.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.MyUserDetailsService;
import ru.kata.spring.boot_security.demo.service.UpdateUserService;
@Service
@RequiredArgsConstructor
public class UpdateUserServiceImpl implements UpdateUserService {
    private final MyUserDetailsService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void setPassword(User user, long id) {
        if (user.getPassword() == null ||
                user.getPassword().equals("") || user.getPassword().equals(userService.findUserById(id).getPassword())) {
            user.setPassword(userService.findUserById(id).getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
    }
}
