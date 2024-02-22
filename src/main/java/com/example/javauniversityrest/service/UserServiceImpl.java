package com.example.javauniversityrest.service;


import com.example.javauniversityrest.dao.UserDao;
import com.example.javauniversityrest.dto.read.UserReadDto;
import com.example.javauniversityrest.dto.save.UserSaveDto;
import com.example.javauniversityrest.model.User;
import com.example.javauniversityrest.security.LoginAuthorization;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl extends BaseService<User, UserReadDto, UserSaveDto, Long, String, String, User, UserSaveDto, UserReadDto> implements LoginAuthorization {
    public UserServiceImpl(UserDao userDao, UserDao userDao1, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils mapperUtils, UserDao userService) {
        super(userDao, userDao1, bCryptPasswordEncoder, mapperUtils);
        this.userService = userService;
    }

    private final UserDao userService;

    public void save(UserSaveDto saveDto){
        save(saveDto, User.class, new UserSaveDto(), User.class);
    }

    public Optional<UserReadDto> findById(Long id) {
        return findById(id, UserReadDto.class);
    }

    @Override
    public User loadUserByUserName(String username) throws UsernameNotFoundException {
        return userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
