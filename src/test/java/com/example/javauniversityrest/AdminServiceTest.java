package com.example.javauniversityrest;

import com.example.javauniversityrest.dao.RepositoryDao;
import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.save.AdminSaveDto;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.Role;
import com.example.javauniversityrest.service.Repository;
import com.example.javauniversityrest.service.Service;
import com.example.javauniversityrest.util.MapperUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class AdminServiceTest extends TestBase{

    RepositoryDao<Admin,Long> repositoryDao;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    MapperUtils mapperUtils;
    Role role1 = new Role("ROLE_ADMIN");
    private final Admin admin = new Admin("Ivanov", "Ivan", "aaa@mail.ru", role1, "12345");

    @Bean
    public Service<Admin, AdminReadDto, AdminSaveDto, Long> service() {
        return new Repository<>(repositoryDao, bCryptPasswordEncoder, mapperUtils);
    };

    @Test
    public void saveTest() {
        AdminSaveDto adminSaveDto = new AdminSaveDto();
        service().save(admin, adminSaveDto);
        System.out.println(service().findById(1L, AdminReadDto.class));
    };


}
