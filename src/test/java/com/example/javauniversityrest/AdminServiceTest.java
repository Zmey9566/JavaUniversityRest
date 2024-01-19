package com.example.javauniversityrest;

import com.example.javauniversityrest.dao.AdminDao;
import com.example.javauniversityrest.dao.UserDao;
import com.example.javauniversityrest.dto.read.*;
import com.example.javauniversityrest.dto.save.*;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.Mentor;
import com.example.javauniversityrest.model.Student;
import com.example.javauniversityrest.model.User;
import com.example.javauniversityrest.service.AdminServiceImpl;
import com.example.javauniversityrest.service.MentorServiceImpl;
import com.example.javauniversityrest.service.StudentServiceImpl;
import com.example.javauniversityrest.service.UserServiceImpl;
import com.example.javauniversityrest.util.MapperUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AdminServiceTest extends TestBase {

    AdminServiceImpl adminService;
    MentorServiceImpl mentorService;
    StudentServiceImpl studentService;
    UserServiceImpl userService;
    AdminDao adminDao;
    UserDao userDao;
    MapperUtils<Admin, AdminReadDto, AdminSaveDto> mapperUtils;

    @Autowired
    public AdminServiceTest(AdminServiceImpl adminService, AdminDao adminDao, MentorServiceImpl mentorService, StudentServiceImpl studentService, MapperUtils<Admin, AdminReadDto, AdminSaveDto> mapperUtils, UserDao userDao, UserServiceImpl userService) {
        this.adminService = adminService;
        this.mentorService = mentorService;
        this.studentService = studentService;
        this.adminDao = adminDao;
        this.mapperUtils = mapperUtils;
        this.userDao = userDao;
        this.userService = userService;
    }

    RoleSaveDto role1 = new RoleSaveDto("ROLE_ADMIN");
    RoleSaveDto role2 = new RoleSaveDto("ROLE_STUDENT");
    RoleReadDto role4 = new RoleReadDto("ROLE_STUDENT");
    RoleSaveDto role3 = new RoleSaveDto("ROLE_MENTOR");

    AdminSaveDto admin = new AdminSaveDto("Ivanov1", "Ivan", "aaa1@mail.ru", role1, "123456");
    AdminSaveDto admin2 = new AdminSaveDto("Ivanov2", "Ivan", "aaa2@mail.ru", role1, "123456");
    AdminSaveDto admin3 = new AdminSaveDto("Ivanov3", "Ivan", "aaa3@mail.ru", role1, "123456");
    StudentSaveDto student1 = new StudentSaveDto("Petrov1", "Ivan", "bbb1@mail.ru", role2, "123456");
    StudentSaveDto student2 = new StudentSaveDto("Petrov2", "Ivan", "bbb2@mail.ru", role2, "123456");
    StudentSaveDto student3 = new StudentSaveDto("Petrov3", "Ivan", "bbb3@mail.ru", role2, "123456");
    MentorSaveDto mentor1 = new MentorSaveDto("Sidorov1", "Ivan", "vvv1@mail.ru", role3, "123456");
    MentorSaveDto mentor2 = new MentorSaveDto("Sidorov2", "Ivan", "vvv2@mail.ru", role3, "123456");
    MentorSaveDto mentor3 = new MentorSaveDto("Sidorov3", "Ivan", "vvv3@mail.ru", role3, "123456");



    @Test
    void saveTest() {
        adminService.save(admin);
        Optional<AdminReadDto> adminDto = adminService.findById(1L);
        assertAll(
                () -> assertEquals(adminDto.get().getFamily(), admin.getFamily()),
                () -> assertEquals(adminDto.get().getName(), admin.getName()),
                () -> assertEquals(adminDto.get().getEmail(), admin.getEmail()),
                () -> assertEquals(adminDto.get().getRole().getName(), admin.getRole().getName())
        );
        Optional<UserReadDto> userDTO = userService.findById(1L);
        System.out.println("USER: " + userDTO);
    }

    @Test
    void getAllTest() {
        adminService.save(admin);
        adminService.save(admin2);
        adminService.save(admin3);
        mentorService.save(mentor1);
        studentService.save(student1);

        assertAll(
                () -> assertEquals(adminService.getAllByModel().size(), 3),
                () -> assertEquals(mentorService.getAllByModel().size(), 1),
                () -> assertEquals(studentService.getAllByModel().size(), 1)
        );
    }

    @Test
    void updateTest() {
        adminService.save(admin);
        adminService.save(admin2);
        adminService.save(admin3);
        List<AdminReadDto> allByModel = adminService.getAllByModel();
        AdminReadDto findedAdmin1 = allByModel.stream().filter(a -> a.getId() == 1).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 1 не найден"));
        AdminReadDto findedAdmin2 = allByModel.stream().filter(a -> a.getId() == 2).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 2 не найден"));
        AdminReadDto findedAdmin3 = allByModel.stream().filter(a -> a.getId() == 3).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 3 не найден"));
        findedAdmin1.setFamily("Petrov");
        findedAdmin2.setName("Dima");
        findedAdmin3.setEmail("bbbb@mail.ru");
        findedAdmin1.setEmail("bbbb@mail.ru");
        findedAdmin1.setPassword("qwerty11");
        findedAdmin1.setRole(role4);
        adminService.update(findedAdmin1, 1L);
        adminService.update(findedAdmin2, 2l);
        adminService.update(findedAdmin3, 3l);

        assertAll(
                () -> assertEquals(findedAdmin1.getFamily(), "Petrov"),
                () -> assertEquals(findedAdmin2.getName(), "Dima"),
                () -> assertEquals(findedAdmin3.getEmail(), "bbbb@mail.ru")
        );

        System.out.println("Admin Before: " + admin);
        System.out.println("Admin After: " + findedAdmin1);
        System.out.println("User After: " + userService.findById(1L));
    }

    @Test
    void deleteByIdTest() {
        adminService.save(admin);
        adminService.save(admin2);
        adminService.save(admin3);

        List<AdminReadDto> allByModel = adminService.getAllByModel();
        AdminReadDto findedAdmin1 = allByModel.stream().filter(a -> a.getId() == 1).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 2 не найден"));
        AdminReadDto findedAdmin2 = allByModel.stream().filter(a -> a.getId() == 2).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 2 не найден"));
        AdminReadDto findedAdmin3 = allByModel.stream().filter(a -> a.getId() == 3).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 2 не найден"));

        adminService.deleteById(2L);

        assertAll(
                () -> assertEquals(adminService.getAllByModel().size(), 2),
                () -> assertEquals(adminService.findById(1L).get().getEmail(), admin.getEmail()),
                () -> assertEquals(adminService.findById(3L).get().getEmail(), admin3.getEmail())
        );
    }

    @Test
    void deleteAllTest() {
        adminService.save(admin);
        adminService.save(admin2);
        adminService.save(admin3);

        adminService.deleteAll();

        assertEquals(adminService.getAllByModel(AdminReadDto.class).size(), 0);
    }

    @Test
    void findByEmailTest() {
        adminService.save(admin);
        adminService.save(admin2);
        adminService.save(admin3);
        Admin byEmail = adminDao.findByEmail("aaa3@mail.ru");
        System.out.println(mapperUtils.mapToModelReadDto(byEmail, AdminReadDto.class));
    }
}
