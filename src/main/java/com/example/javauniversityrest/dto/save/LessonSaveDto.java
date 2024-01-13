package com.example.javauniversityrest.dto.save;

import com.example.javauniversityrest.model.MentorStudentLesson;
import lombok.Data;

@Data
public class LessonSaveDto {

    private String subject;
    private int timePerSubject;
    private int capacity;
    private int minLevel;
    private MentorStudentLesson mentorStudentLesson;
}
