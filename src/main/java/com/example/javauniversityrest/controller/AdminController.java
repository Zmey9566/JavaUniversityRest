package com.example.javauniversityrest.controller;

import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.save.AdminSaveDto;
import com.example.javauniversityrest.service.AdminServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admins")
public class AdminController {

    private final AdminServiceImpl adminService;

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<AdminReadDto>> showAdmins() {
        return new ResponseEntity<>(adminService.getAllByModel(), HttpStatus.OK);
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
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable Long id) {
        adminService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateAdmin(@RequestBody @NotNull AdminReadDto adminReadDto, @PathVariable Long id) {
        adminService.update(adminReadDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
