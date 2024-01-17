package com.example.javauniversityrest.service;

import com.example.javauniversityrest.dao.RoleDao;
import com.example.javauniversityrest.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void add(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Role> getListRoles() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> getRolesListById(List<Integer> id) {
        return roleDao.findAllById(id);
    }

    @Override
    public Optional<Role> getRoleById(int id) {
        return roleDao.findById(id);
    }
}
