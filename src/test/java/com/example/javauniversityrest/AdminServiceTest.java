package com.example.javauniversityrest;

import com.example.javauniversityrest.dao.AdminDao;
import com.example.javauniversityrest.dao.UserDao;
import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.read.MentorReadDto;
import com.example.javauniversityrest.dto.read.StudentReadDto;
import com.example.javauniversityrest.dto.save.*;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.Mentor;
import com.example.javauniversityrest.model.Student;
import com.example.javauniversityrest.model.User;
import com.example.javauniversityrest.service.AdminServiceImpl;
import com.example.javauniversityrest.service.MentorServiceImpl;
import com.example.javauniversityrest.service.StudentServiceImpl;
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
    AdminDao adminDao;
    UserDao userDao;
    MapperUtils<Admin, AdminReadDto, AdminSaveDto> mapperUtils;

    @Autowired
    public AdminServiceTest(AdminServiceImpl adminService, AdminDao adminDao, MentorServiceImpl mentorService, StudentServiceImpl studentService, MapperUtils<Admin, AdminReadDto, AdminSaveDto> mapperUtils, UserDao userDao) {
        this.adminService = adminService;
        this.mentorService = mentorService;
        this.studentService = studentService;
        this.adminDao = adminDao;
        this.mapperUtils = mapperUtils;
        this.userDao = userDao;
    }

    RoleSaveDto role1 = new RoleSaveDto("ROLE_ADMIN");
    RoleSaveDto role2 = new RoleSaveDto("ROLE_STUDENT");
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
    UserSaveDto userSaveDto = new UserSaveDto();



    @Test
    void saveTest() {
        adminService.save(admin, Admin.class, userSaveDto, User.class);
        Optional<AdminReadDto> adminDto = adminService.findById(1L, AdminReadDto.class);
        assertAll(
                () -> assertEquals(adminDto.get().getFamily(), admin.getFamily()),
                () -> assertEquals(adminDto.get().getName(), admin.getName()),
                () -> assertEquals(adminDto.get().getEmail(), admin.getEmail()),
                () -> assertEquals(adminDto.get().getRole().getName(), admin.getRole().getName())
        );
        System.out.println(userDao.findById(1L));
    }

    @Test
    void getAllTest() {
        adminService.save(admin, Admin.class, userSaveDto, User.class);
        adminService.save(admin2, Admin.class, userSaveDto, User.class);
        adminService.save(admin3, Admin.class, userSaveDto, User.class);
        mentorService.save(mentor1, Mentor.class, userSaveDto, User.class);
        studentService.save(student1, Student.class, userSaveDto, User.class);

        assertAll(
                () -> assertEquals(adminService.getAllByModel(AdminReadDto.class).size(), 3),
                () -> assertEquals(mentorService.getAllByModel(MentorReadDto.class).size(), 1),
                () -> assertEquals(studentService.getAllByModel(StudentReadDto.class).size(), 1)
        );
    }

    @Test
    void updateTest() {
        adminService.save(admin, Admin.class, userSaveDto, User.class);
        adminService.save(admin2, Admin.class, userSaveDto, User.class);
        adminService.save(admin3, Admin.class, userSaveDto, User.class);
        List<AdminReadDto> allByModel = adminService.getAllByModel(AdminReadDto.class);
        AdminReadDto findedAdmin1 = allByModel.stream().filter(a -> a.getId() == 1).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 1 не найден"));
        AdminReadDto findedAdmin2 = allByModel.stream().filter(a -> a.getId() == 2).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 2 не найден"));
        AdminReadDto findedAdmin3 = allByModel.stream().filter(a -> a.getId() == 3).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 3 не найден"));
        findedAdmin1.setFamily("Petrov");
        findedAdmin2.setName("Dima");
        findedAdmin3.setEmail("bbbb@mail.ru");
        findedAdmin1.setPassword("qwerty11");
        adminService.update(findedAdmin1, 1l, Admin.class);
        adminService.update(findedAdmin2, 2l, Admin.class);
        adminService.update(findedAdmin3, 3l, Admin.class);

        assertAll(
                () -> assertEquals(findedAdmin1.getFamily(), "Petrov"),
                () -> assertEquals(findedAdmin2.getName(), "Dima"),
                () -> assertEquals(findedAdmin3.getEmail(), "bbbb@mail.ru")
        );
    }

    @Test
    void deleteByIdTest() {
        adminService.save(admin, Admin.class, userSaveDto, User.class);
        adminService.save(admin2, Admin.class, userSaveDto, User.class);
        adminService.save(admin3, Admin.class, userSaveDto, User.class);

        List<AdminReadDto> allByModel = adminService.getAllByModel(AdminReadDto.class);
        AdminReadDto findedAdmin1 = allByModel.stream().filter(a -> a.getId() == 1).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 2 не найден"));
        AdminReadDto findedAdmin2 = allByModel.stream().filter(a -> a.getId() == 2).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 2 не найден"));
        AdminReadDto findedAdmin3 = allByModel.stream().filter(a -> a.getId() == 3).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 2 не найден"));

        adminService.deleteById(2L);

        assertAll(
                () -> assertEquals(adminService.getAllByModel(AdminReadDto.class).size(), 2),
                () -> assertEquals(adminService.findById(1L, AdminReadDto.class).get().getEmail(), admin.getEmail()),
                () -> assertEquals(adminService.findById(3L, AdminReadDto.class).get().getEmail(), admin3.getEmail())
        );
    }

    @Test
    void deleteAllTest() {
        adminService.save(admin, Admin.class, userSaveDto, User.class);
        adminService.save(admin2, Admin.class, userSaveDto, User.class);
        adminService.save(admin3, Admin.class, userSaveDto, User.class);

        adminService.deleteAll();

        assertEquals(adminService.getAllByModel(AdminReadDto.class).size(), 0);
    }

    @Test
    void findByEmailTest() {
        adminService.save(admin, Admin.class, userSaveDto, User.class);
        adminService.save(admin2, Admin.class, userSaveDto, User.class);
        adminService.save(admin3, Admin.class, userSaveDto, User.class);
        Admin byEmail = adminDao.findByEmail("aaa3@mail.ru");
        System.out.println(mapperUtils.mapToModelReadDto(byEmail, AdminReadDto.class));
    }
}
