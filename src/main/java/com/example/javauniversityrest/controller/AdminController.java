package com.example.javauniversityrest.controller;

import com.example.javauniversityrest.dao.RoleDao;
import com.example.javauniversityrest.dao.UserDao;
import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.save.AdminSaveDto;
import com.example.javauniversityrest.service.AdminServiceImpl;
import com.example.javauniversityrest.service.MentorServiceImpl;
import com.example.javauniversityrest.service.StudentServiceImpl;
import com.example.javauniversityrest.util.MapperUtils;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminServiceImpl adminService;
    private final MentorServiceImpl mentorService;
    private final StudentServiceImpl studentService;
    private final UserDao userDao;
    private final MapperUtils mapperUtils;

    @Autowired
    public AdminController(AdminServiceImpl adminService, MentorServiceImpl mentorService, StudentServiceImpl studentService, UserDao userDao, MapperUtils mapperUtils) {
        this.adminService = adminService;
        this.mentorService = mentorService;
        this.studentService = studentService;
        this.userDao = userDao;
        this.mapperUtils = mapperUtils;
    }

    @GetMapping
    public ResponseEntity<List<AdminReadDto>> showAdmins() {
        final var adminByModel = adminService.getAllByModel();
        return new ResponseEntity<>(adminByModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AdminReadDto>> showAdmin(@PathVariable("id") Long id) {
        return new ResponseEntity<>(adminService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addNewAdmin(@RequestBody AdminSaveDto adminSaveDto) {
        adminService.save(adminSaveDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable("id") Long id) {
        adminService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateAdmin(@RequestBody @NotNull AdminReadDto adminReadDto, @PathVariable Long id) {
        adminService.update(adminReadDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
