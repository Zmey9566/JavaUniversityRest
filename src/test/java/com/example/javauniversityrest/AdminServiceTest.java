package com.example.javauniversityrest;

import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.save.AdminSaveDto;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.Role;
import com.example.javauniversityrest.service.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
public class AdminServiceTest extends TestBase {

    AdminServiceImpl adminService;

    @Autowired
    public AdminServiceTest(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    Role role1 = new Role("ROLE_ADMIN");
    private final Admin admin = new Admin("Ivanov", "Ivan", "aaa@mail.ru", role1, "12345");


    @Test
    public void saveTest() {
        AdminSaveDto adminSaveDto = new AdminSaveDto();
        adminService.save(admin, adminSaveDto);
        System.out.println(adminService.findById(1L, AdminReadDto.class));
    };


}
