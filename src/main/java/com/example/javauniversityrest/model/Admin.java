package com.example.javauniversityrest.model;

import com.example.javauniversityrest.dto.save.AdminSaveDto;
import com.example.javauniversityrest.service.GetSet;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@ToString
@Component
public class Admin {

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

    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

    private String password;

    public Admin(String family, String name, String email, Role role,String password) {
        this.family = family;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    @Bean
    GetSet<Admin, String> getSet1() {
        return new GetSet<Admin, String>() {
            @Override
            public String getPassword(Admin model) {
                return model.getPassword();
            }

            @Override
            public void setPassword(Admin model, String password) {
                model.setPassword(password);
            }
        };
    }
}
