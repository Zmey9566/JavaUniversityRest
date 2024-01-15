package com.example.javauniversityrest.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@ToString(exclude = {"adminRoles"})
@RequiredArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "role")
    private final List<Admin> adminRoles = new ArrayList<>();
    @OneToMany(mappedBy = "role")
    private final List<Mentor> mentorRoles = new ArrayList<>();
    @OneToMany(mappedBy = "role")
    private final List<Student> studentRoles = new ArrayList<>();
    @OneToMany(mappedBy = "role")
    private final List<User> userRoles = new ArrayList<>();

    @Override
    public String getAuthority() {
        return name;
    }

    public Role(String name) {
        this.name = name;
    }
}
