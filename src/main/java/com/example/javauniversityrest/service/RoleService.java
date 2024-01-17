package com.example.javauniversityrest.service;

import com.example.javauniversityrest.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    void add(Role role);

    List<Role> getListRoles();

    List<Role> getRolesListById(List<Integer> id);

    Optional<Role> getRoleById(int id);
}
