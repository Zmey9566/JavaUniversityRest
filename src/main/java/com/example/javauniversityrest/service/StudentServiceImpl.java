package com.example.javauniversityrest.service;

import com.example.javauniversityrest.dao.StudentDao;
import com.example.javauniversityrest.dao.UserDao;
import com.example.javauniversityrest.dto.read.MentorReadDto;
import com.example.javauniversityrest.dto.read.StudentReadDto;
import com.example.javauniversityrest.dto.save.MentorSaveDto;
import com.example.javauniversityrest.dto.save.StudentSaveDto;
import com.example.javauniversityrest.dto.save.UserSaveDto;
import com.example.javauniversityrest.model.Mentor;
import com.example.javauniversityrest.model.Student;
import com.example.javauniversityrest.model.User;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends BaseService<Student, StudentReadDto, StudentSaveDto, Long, String, String, User, UserSaveDto> {
    public StudentServiceImpl(StudentDao studentDao, UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils<Mentor, MentorReadDto, MentorSaveDto> mapperUtils) {
        super(studentDao, userDao, bCryptPasswordEncoder, mapperUtils);
    }
}
