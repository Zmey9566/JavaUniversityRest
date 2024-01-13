package com.example.javauniversityrest.dao;

import com.example.javauniversityrest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
}
