package com.example.javauniversityrest.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@ToString(exclude = {"adminRoles", "mentorRoles", "studentRoles", "userRoles"})
@RequiredArgsConstructor
@Component
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
        return getName();
    }

    public Role(String name) {
        this.name = name;
    }
}
