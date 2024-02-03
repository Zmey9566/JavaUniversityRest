package com.example.javauniversityrest.controller;

import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.read.RoleReadDto;
import com.example.javauniversityrest.service.RoleServiceImpl;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {
    private final RoleServiceImpl roleService;
    private final MapperUtils mapperUtils;

    @Autowired
    public RoleController(RoleServiceImpl roleService, MapperUtils mapperUtils) {
        this.roleService = roleService;
        this.mapperUtils = mapperUtils;
    }

    @GetMapping
    public ResponseEntity<List<RoleReadDto>> showRoles() {
        final var roles = roleService.getListRoles().stream()
                .map(role -> (RoleReadDto)mapperUtils.mapToModelReadDto(role, RoleReadDto.class)).toList();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
