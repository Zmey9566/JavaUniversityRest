package com.example.javauniversityrest.dao;

import com.example.javauniversityrest.model.MentorStudentLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorStudentLessonDao extends JpaRepository<MentorStudentLesson, Long> {
}
