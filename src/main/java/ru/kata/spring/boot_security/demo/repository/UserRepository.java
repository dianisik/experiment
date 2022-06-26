package ru.kata.spring.boot_security.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query ("SELECT p FROM User p JOIN FETCH p.roles where p.name = (:name)")
    User findByName(String name);
    User findUserById(Long id);


}
