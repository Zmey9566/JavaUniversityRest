package com.example.javauniversityrest.controller;

import com.example.javauniversityrest.dao.AdminDao;
import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.save.AdminSaveDto;
import com.example.javauniversityrest.dto.save.UserSaveDto;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.User;
import com.example.javauniversityrest.service.AdminServiceImpl;
import com.example.javauniversityrest.service.UserServiceImpl;
import com.example.javauniversityrest.util.MapperUtils;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/forAdmin")
public class AdminController {

    private final AdminServiceImpl adminService;

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<AdminReadDto>> showAdmins() {
        List<AdminReadDto> admins = new ArrayList<>();
        for (AdminReadDto admin : adminService.getAllByModel()) {
            admins.add(admin);
        }
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<Optional<AdminReadDto>> showAdmin(@PathVariable("id") Long id) {
        return new ResponseEntity<>(adminService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/admins/addNewAdmin")
    public ResponseEntity<HttpStatus> addNewAdmin(@RequestBody AdminSaveDto adminSaveDto) {
        adminService.save(adminSaveDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable Long id) {
        adminService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/admins/{id}")
    public ResponseEntity<HttpStatus> updateAdmin(@RequestBody @NotNull AdminReadDto adminReadDto, @PathVariable Long id) {
        adminService.update(adminReadDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
