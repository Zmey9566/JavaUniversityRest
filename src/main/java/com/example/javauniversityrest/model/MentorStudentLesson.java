package com.example.javauniversityrest.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mentor_student_lesson")
@Data
public class MentorStudentLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Lesson lesson;
    @ManyToOne
    private Mentor mentor;
    @ManyToOne
    private Student student;
}
