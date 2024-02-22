package com.example.javauniversityrest.model;

import com.example.javauniversityrest.service.PersonGetSet;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@ToString
@Component
public class Admin implements PersonGetSet<Role> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Заполните поле Фамилия")
    private String family;

    @NotEmpty(message = "Заполните поле Имя")
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Role role;

    private String password;

    public Admin(String family, String name, String email, Role role,String password) {
        this.family = family;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    @Override
    public Long getModelId() {
        return id;
    }

    @Override
    public void setModelId(Long modelId) {

    }
}
