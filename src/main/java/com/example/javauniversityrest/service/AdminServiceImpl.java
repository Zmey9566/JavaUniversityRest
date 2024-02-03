package com.example.javauniversityrest.service;
import com.example.javauniversityrest.dao.AdminDao;
import com.example.javauniversityrest.dao.RoleDao;
import com.example.javauniversityrest.dao.UserDao;
import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.read.RoleReadDto;
import com.example.javauniversityrest.dto.read.UserReadDto;
import com.example.javauniversityrest.dto.save.AdminSaveDto;
import com.example.javauniversityrest.dto.save.RoleSaveDto;
import com.example.javauniversityrest.dto.save.UserSaveDto;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.Role;
import com.example.javauniversityrest.model.User;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class AdminServiceImpl extends BaseService<Admin, AdminReadDto, AdminSaveDto, Long, String, String, User, UserSaveDto, UserReadDto> {

    private final UserDao userRepository;
    private final AdminDao adminRepository;
    private final MapperUtils<User, UserReadDto, UserSaveDto> userMapper;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao, UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils<Admin, AdminReadDto, AdminSaveDto> mapperUtils, UserDao userRepository, AdminDao adminRepository, MapperUtils<User, UserReadDto, UserSaveDto> userMapper) {
        super(adminDao, userDao, bCryptPasswordEncoder, mapperUtils);
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.userMapper = userMapper;
    }

    public void save(AdminSaveDto saveDto) {
        save(saveDto, Admin.class, new UserSaveDto(), User.class);
    }

    public void update(AdminReadDto readDto, Long id) {
        final var byEmail = userRepository.findByEmail(adminRepository.findById(id).get().getEmail());
        final var userReadDto = userMapper.mapToModelReadDto(byEmail, UserReadDto.class);
        update(readDto, id, Admin.class, userReadDto, User.class);
    }

    public Optional<AdminReadDto> findById(Long id) {
        return findById(id, AdminReadDto.class);
    }

    public List<AdminReadDto> getAllByModel() {
        return getAllByModel(AdminReadDto.class);
    }
}
