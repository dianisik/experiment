package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.*;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    public MyUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }

    public Set<Role> getRoles (ArrayList<String> rolesToString){
        List<Long> rolesId = new ArrayList<>();
        for(String i:rolesToString){
            rolesId.add(Long.valueOf(i));
        }
        return roleRepository.findByIdIn(rolesId);
    }
    public User findByUserName(String userName){
        return userRepository.findByName(userName);    }

    @Override
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {
        return userRepository.findByName(name);

    }

    @Override
    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void saveAndFlush(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

   @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}