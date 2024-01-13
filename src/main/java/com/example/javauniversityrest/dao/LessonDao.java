package com.example.javauniversityrest.dao;

import com.example.javauniversityrest.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonDao extends JpaRepository<Lesson, Long> {
}
