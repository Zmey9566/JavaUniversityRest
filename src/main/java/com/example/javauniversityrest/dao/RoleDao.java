package com.example.javauniversityrest.dao;

import com.example.javauniversityrest.dto.save.RoleSaveDto;
import com.example.javauniversityrest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findById(int id);
}
