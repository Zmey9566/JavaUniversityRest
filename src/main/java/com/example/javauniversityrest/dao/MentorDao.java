package com.example.javauniversityrest.dao;

import com.example.javauniversityrest.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorDao extends JpaRepository<Mentor, Long> {
}
