package com.example.javauniversityrest.dao;

import com.example.javauniversityrest.model.Mentor;
import com.example.javauniversityrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    void deleteByModelIdAndRoleId(Long modelId, Integer roleId);
}
