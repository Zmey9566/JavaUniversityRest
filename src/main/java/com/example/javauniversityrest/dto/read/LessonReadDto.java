package com.example.javauniversityrest.dto.read;

import com.example.javauniversityrest.model.MentorStudentLesson;
import lombok.Data;

@Data
public class LessonReadDto {

    private Long id;
    private String subject;
    private int timePerSubject;
    private int capacity;
    private int minLevel;
    private MentorStudentLesson mentorStudentLesson;
}
