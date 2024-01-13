package com.example.javauniversityrest.service;

import com.example.javauniversityrest.dao.RepositoryDao;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MentorServiceImpl extends Repository{
    public MentorServiceImpl(RepositoryDao repositoryDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils mapperUtils) {
        super(repositoryDao, bCryptPasswordEncoder, mapperUtils);
    }

}
