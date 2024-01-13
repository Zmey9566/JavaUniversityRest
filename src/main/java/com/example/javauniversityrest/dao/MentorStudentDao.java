package com.example.javauniversityrest.dao;

import com.example.javauniversityrest.model.MentorStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorStudentDao extends JpaRepository<MentorStudent, Long> {
}
