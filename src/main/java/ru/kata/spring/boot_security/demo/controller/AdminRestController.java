package ru.kata.spring.boot_security.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.MyUserDetailsService;
import ru.kata.spring.boot_security.demo.service.UpdateUserService;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class AdminRestController {

    private final MyUserDetailsService userService;

    private final UpdateUserService updateUserService;

    private final PasswordEncoder passwordEncoder;


    @GetMapping("/api/principal")
    public User getPrincipalInfo(Principal principal) {
        return userService.findByUserName(principal.getName());
    }

    @GetMapping("/api/{id}")
    public User findOneUser(@PathVariable long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/api")
    public ResponseEntity addNewUser(@RequestBody JsonNode jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        User user = mapper.readValue(jsonString.get("user").traverse(),User.class);
        ArrayList rolesId = mapper.readValue(jsonString.get("roles").traverse(),ArrayList.class);
        Set<Role> roleSet = userService.getRoles(rolesId);
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).body(user);
    }
    @GetMapping("/api")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PutMapping("/api/{id}")
    public User updateUser(@RequestBody JsonNode jsonString, @PathVariable("id") long id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jsonString.get("data").traverse(),User.class);
        ArrayList rolesId = mapper.readValue(jsonString.get("roles").traverse(),ArrayList.class);
        Set<Role> roleSet = userService.getRoles(rolesId);
        user.setRoles(roleSet);
        updateUserService.setPassword(user, id);
        userService.saveAndFlush(user);
        return user;
    }
    @DeleteMapping("/api/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteById(id);
    }
}
