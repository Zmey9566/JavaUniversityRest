package com.example.javauniversityrest.service;


import com.example.javauniversityrest.dao.MentorDao;
import com.example.javauniversityrest.dto.read.MentorReadDto;
import com.example.javauniversityrest.dto.save.MentorSaveDto;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.Mentor;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MentorServiceImpl extends Repository<Mentor, MentorReadDto, MentorSaveDto, Long, String, String>{
    public MentorServiceImpl(MentorDao mentorDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils<Mentor, MentorReadDto, MentorSaveDto> mapperUtils, @Qualifier("getSet2") GetSet<Mentor, String> getSet) {
        super(mentorDao, bCryptPasswordEncoder, mapperUtils, getSet);
    }

}
