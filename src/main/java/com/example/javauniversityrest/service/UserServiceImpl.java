package com.example.javauniversityrest.service;


import com.example.javauniversityrest.dao.UserDao;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseService {
    public UserServiceImpl(UserDao userDao, UserDao userDao1, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils mapperUtils) {
        super(userDao, userDao1, bCryptPasswordEncoder, mapperUtils);
    }
}
