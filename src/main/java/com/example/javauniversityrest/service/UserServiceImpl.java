package com.example.javauniversityrest.service;

import com.example.javauniversityrest.dao.RepositoryDao;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl extends Repository{
    public UserServiceImpl(RepositoryDao repositoryDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils mapperUtils) {
        super(repositoryDao, bCryptPasswordEncoder, mapperUtils);
    }
}
