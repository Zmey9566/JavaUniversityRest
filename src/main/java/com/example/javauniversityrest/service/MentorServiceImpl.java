package com.example.javauniversityrest.service;


import com.example.javauniversityrest.dao.MentorDao;
import com.example.javauniversityrest.dao.UserDao;
import com.example.javauniversityrest.dto.read.MentorReadDto;
import com.example.javauniversityrest.dto.save.MentorSaveDto;
import com.example.javauniversityrest.dto.save.UserSaveDto;
import com.example.javauniversityrest.model.Mentor;
import com.example.javauniversityrest.model.User;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MentorServiceImpl extends BaseService<Mentor, MentorReadDto, MentorSaveDto, Long, String, String, User, UserSaveDto> {
    public MentorServiceImpl(MentorDao mentorDao, UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils<Mentor, MentorReadDto, MentorSaveDto> mapperUtils) {
        super(mentorDao, userDao, bCryptPasswordEncoder, mapperUtils);
    }

}
