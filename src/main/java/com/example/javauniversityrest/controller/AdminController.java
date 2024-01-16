//package com.example.javauniversityrest.controller;
//
//import com.example.javauniversityrest.dto.read.AdminReadDto;
//import com.example.javauniversityrest.dto.save.AdminSaveDto;
//import com.example.javauniversityrest.model.Admin;
//import com.example.javauniversityrest.service.AdminServiceImpl;
//import com.example.javauniversityrest.util.MapperUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("api/forAdmin")
//public class AdminController {
//
//    private final AdminServiceImpl adminService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final MapperUtils<Admin, AdminReadDto, AdminSaveDto> mapperUtils;
//
////    @Autowired
//    public AdminController(AdminServiceImpl adminService, BCryptPasswordEncoder bCryptPasswordEncoder,
//                           MapperUtils<Admin, AdminReadDto, AdminSaveDto> mapperUtils) {
//        this.adminService = adminService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.mapperUtils = mapperUtils;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<AdminReadDto>> showAdmins() {
//        List<AdminReadDto> admins = new ArrayList<>();
//        for (AdminReadDto admin : adminService.getAllByModel(AdminReadDto.class)) {
//            admins.add(admin);
//        }
//        return new ResponseEntity<>(admins, HttpStatus.OK);
//    }
//
//    @GetMapping("/admins/{id}")
//    public ResponseEntity<Optional<AdminReadDto>> showAdmin(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(adminService.findById(id, AdminReadDto.class), HttpStatus.OK);
//    }
//
//    @PostMapping("/admins/addNewAdmin")
//    public ResponseEntity<HttpStatus> addNewAdmin(@RequestBody AdminSaveDto adminSaveDto) {
//        return null;
//    }
//}
