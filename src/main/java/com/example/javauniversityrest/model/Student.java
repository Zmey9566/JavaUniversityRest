package com.example.javauniversityrest.model;

import com.example.javauniversityrest.service.GetSet;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@ToString
@Component
public class Student {

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
    private String password;
    private int level;
    @OneToMany(mappedBy = "student")
    private final List<MentorStudent> mentorStudentList = new ArrayList<>();
    @OneToMany(mappedBy = "student")
    private final List<MentorStudentLesson> mentorStudentLessonList = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

    @Bean
    GetSet<Student, String> getSet3() {
        return new GetSet<Student, String>() {
            @Override
            public String getPassword(Student model) {
                return model.getPassword();
            }

            @Override
            public void setPassword(Student model, String password) {
                model.setPassword(password);
            }
        };
    }
}
