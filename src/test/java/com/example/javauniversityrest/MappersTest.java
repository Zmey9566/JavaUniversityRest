package com.example.javauniversityrest;

import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.save.AdminSaveDto;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.Role;
import com.example.javauniversityrest.util.MapperUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MappersTest {

    @Bean
    public MapperUtils<Admin, AdminReadDto, AdminSaveDto> adminMapper() {
        return new MapperUtils<>();
    }

    Role role1 = new Role("ROLE_ADMIN");
    private final Admin admin = new Admin("Ivanov", "Ivan", "aaa@mail.ru", role1, "12345");

    @Test
    public void adminToDtoTest(){
        final var adminDto = adminMapper().mapToAdminReadDto(admin);
        assertAll(
                () -> assertEquals(adminDto.getFamily(), admin.getFamily()),
                () -> assertEquals(adminDto.getName(), admin.getName()),
                () -> assertEquals(adminDto.getEmail(), admin.getEmail())
        );
    }

}
