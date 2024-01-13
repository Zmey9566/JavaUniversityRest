package com.example.javauniversityrest.dao;

import com.example.javauniversityrest.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, Long> {
}
