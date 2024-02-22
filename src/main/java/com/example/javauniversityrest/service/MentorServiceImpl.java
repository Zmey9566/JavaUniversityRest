package com.example.javauniversityrest.service;


import com.example.javauniversityrest.dao.MentorDao;
import com.example.javauniversityrest.dao.UserDao;
import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.read.MentorReadDto;
import com.example.javauniversityrest.dto.read.UserReadDto;
import com.example.javauniversityrest.dto.save.MentorSaveDto;
import com.example.javauniversityrest.dto.save.UserSaveDto;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.Mentor;
import com.example.javauniversityrest.model.User;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class MentorServiceImpl extends BaseService<Mentor, MentorReadDto, MentorSaveDto, Long, String, String, User, UserSaveDto, UserReadDto> {

    private final UserDao userRepository;
    private final MentorDao mentorRepository;
//    private final UserDao userDao;
    private final MapperUtils<User, UserReadDto, User> userMapper;

    public MentorServiceImpl(MentorDao mentorDao, UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils<Mentor, MentorReadDto, MentorSaveDto> mapperUtils, UserDao userRepository, MentorDao mentorRepository, MapperUtils<User, UserReadDto, User> userMapper) {
        super(mentorDao, userDao, bCryptPasswordEncoder, mapperUtils);
        this.userRepository = userRepository;
        this.mentorRepository = mentorRepository;
        this.userMapper = userMapper;
    }

    public void save(MentorSaveDto saveDto) {
        save(saveDto, Mentor.class, new UserSaveDto(), User.class);
    }

    public void update(MentorReadDto readDto, Long id) {
        final var byEmail = userRepository.findByEmail(mentorRepository.findById(id).get().getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        final var userReadDto = userMapper.mapToModelReadDto(byEmail, UserReadDto.class);
        update(readDto, id, Mentor.class, userReadDto, User.class);
    }

    public Optional<MentorReadDto> findById(Long id) {
        return findById(id, MentorReadDto.class);
    }

    public List<MentorReadDto> getAllByModel() {
        return getAllByModel(MentorReadDto.class);
    }

    @Override
    public void deleteById(Long id) {
        final var mentorForDel = mentorRepository.findById(id).map(m -> m.getRole().getId()).orElseThrow(() -> new IllegalArgumentException());
        super.deleteById(id);
        userRepository.deleteByModelIdAndRoleId(id, mentorForDel);

    }
}
