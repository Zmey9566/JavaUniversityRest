package com.example.javauniversityrest.service;
import com.example.javauniversityrest.dao.AdminDao;
import com.example.javauniversityrest.dao.UserDao;
import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.save.AdminSaveDto;
import com.example.javauniversityrest.dto.save.UserSaveDto;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.User;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends BaseService<Admin, AdminReadDto, AdminSaveDto, Long, String, String, User, UserSaveDto> {

    @Autowired
    public AdminServiceImpl(AdminDao adminDao, UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils<Admin, AdminReadDto, AdminSaveDto> mapperUtils) {
        super(adminDao, userDao, bCryptPasswordEncoder, mapperUtils);
    }

}
