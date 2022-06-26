package ru.kata.spring.boot_security.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
public class Role implements GrantedAuthority  {

    public Role(){
    }
    public Role(String name){
        this.name = name;
    }
    public Role(Long id, String name){
        this.id = id; this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "name")
    private String name;
    @Override
    public String getAuthority() {
        return name;
    }
}
