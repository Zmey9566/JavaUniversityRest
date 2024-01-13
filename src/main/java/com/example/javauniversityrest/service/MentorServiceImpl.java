package com.example.javauniversityrest.service;


import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MentorServiceImpl extends Repository{
    public MentorServiceImpl(JpaRepository repositoryDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils mapperUtils) {
        super(repositoryDao, bCryptPasswordEncoder, mapperUtils);
    }

}
