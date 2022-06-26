package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
@Service
public interface UpdateUserService {

    void setPassword (User user, long id);
}
