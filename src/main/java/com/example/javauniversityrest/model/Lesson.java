package com.example.javauniversityrest.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lesson")
@Data
@NoArgsConstructor
@ToString
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private int timePerSubject;
    private int capacity;
    private int minLevel;
    @OneToMany(mappedBy = "lesson")
    private final List<MentorStudentLesson> mentorStudentLessonList = new ArrayList<>();
}
