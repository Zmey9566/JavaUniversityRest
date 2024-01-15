package com.example.javauniversityrest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mentor")
@Data
@NoArgsConstructor
@ToString
public class Mentor {

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
    @OneToMany
    private final List<MentorStudent> mentorStudentList = new ArrayList<>();
    @OneToMany(mappedBy = "mentor")
    private final List<MentorStudentLesson> mentorStudentLessonList = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;
}
