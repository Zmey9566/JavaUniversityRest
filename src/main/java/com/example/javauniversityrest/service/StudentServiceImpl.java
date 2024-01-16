package com.example.javauniversityrest.service;

import com.example.javauniversityrest.dao.StudentDao;
import com.example.javauniversityrest.dto.read.MentorReadDto;
import com.example.javauniversityrest.dto.read.StudentReadDto;
import com.example.javauniversityrest.dto.save.MentorSaveDto;
import com.example.javauniversityrest.dto.save.StudentSaveDto;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.Mentor;
import com.example.javauniversityrest.model.Student;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends Repository<Student, StudentReadDto, StudentSaveDto, Long, String, String>{
    public StudentServiceImpl(StudentDao studentDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils<Mentor, MentorReadDto, MentorSaveDto> mapperUtils, @Qualifier("getSet3") GetSet<Student, String> getSet) {
        super(studentDao, bCryptPasswordEncoder, mapperUtils, getSet);
    }
}
