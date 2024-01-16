package com.example.javauniversityrest.service;
import com.example.javauniversityrest.dao.AdminDao;
import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.save.AdminSaveDto;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends Repository<Admin, AdminReadDto, AdminSaveDto, Long, String, String> {

    @Autowired
    public AdminServiceImpl(AdminDao adminDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils<Admin, AdminReadDto, AdminSaveDto> mapperUtils, @Qualifier("getSet1") GetSet<Admin, String> getSet) {
        super(adminDao, bCryptPasswordEncoder, mapperUtils, getSet);
    }

}
