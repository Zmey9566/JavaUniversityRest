package com.example.javauniversityrest.controller;

import com.example.javauniversityrest.dto.read.StudentReadDto;
import com.example.javauniversityrest.dto.save.StudentSaveDto;
import com.example.javauniversityrest.service.StudentServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentServiceImpl studentService;
    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentReadDto>> showAdmins() {
        return new ResponseEntity<>(studentService.getAllByModel(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<StudentReadDto>> showAdmin(@PathVariable("id") Long id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addNewAdmin(@RequestBody StudentSaveDto studentSaveDto) {
        studentService.save(studentSaveDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable Long id) {
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateAdmin(@RequestBody @NotNull StudentReadDto studentReadDto, @PathVariable Long id) {
        studentService.update(studentReadDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
