package com.example.javauniversityrest.service;


import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.User;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl extends Repository {
    public UserServiceImpl(JpaRepository repositoryDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils mapperUtils, GetSet<User, String> getSet) {
        super(repositoryDao, bCryptPasswordEncoder, mapperUtils, getSet);
    }
}
