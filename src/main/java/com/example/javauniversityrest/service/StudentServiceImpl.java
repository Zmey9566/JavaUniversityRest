package com.example.javauniversityrest.service;

import com.example.javauniversityrest.dao.StudentDao;
import com.example.javauniversityrest.dao.UserDao;
import com.example.javauniversityrest.dto.read.MentorReadDto;
import com.example.javauniversityrest.dto.read.StudentReadDto;
import com.example.javauniversityrest.dto.read.UserReadDto;
import com.example.javauniversityrest.dto.save.MentorSaveDto;
import com.example.javauniversityrest.dto.save.StudentSaveDto;
import com.example.javauniversityrest.dto.save.UserSaveDto;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.Mentor;
import com.example.javauniversityrest.model.Student;
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
public class StudentServiceImpl extends BaseService<Student, StudentReadDto, StudentSaveDto, Long, String, String, User, UserSaveDto, UserReadDto> {

    private final StudentDao studentRepository;
    private final MapperUtils<User, UserReadDto, UserSaveDto> userMapper;
    private final UserDao userRepository;
    public StudentServiceImpl(StudentDao studentDao, UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils<Mentor, MentorReadDto, MentorSaveDto> mapperUtils, StudentDao studentRepository, MapperUtils<User, UserReadDto, UserSaveDto> userMapper, UserDao userRepository) {
        super(studentDao, userDao, bCryptPasswordEncoder, mapperUtils);
        this.studentRepository = studentRepository;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public void save(StudentSaveDto saveDto) {
        save(saveDto, Student.class, new UserSaveDto(), User.class);
    }

    public void update(StudentReadDto readDto, Long id) {
        final var byEmail = userRepository.findByEmail(studentRepository.findById(id).get().getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        final var userReadDto = userMapper.mapToModelReadDto(byEmail, UserReadDto.class);
        update(readDto, id, Student.class, userReadDto, User.class);
    }

    public Optional<StudentReadDto> findById(Long id) {
        return findById(id, StudentReadDto.class);
    }

    public List<StudentReadDto> getAllByModel() {
        return getAllByModel(StudentReadDto.class);
    }

    @Override
    public void deleteById(Long id) {
        final var studentForDel = studentRepository.findById(id).map(s -> s.getRole().getId()).orElseThrow(() -> new IllegalArgumentException());
        super.deleteById(id);
        userRepository.deleteByModelIdAndRoleId(id, studentForDel);
    }
}
