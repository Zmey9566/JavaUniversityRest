package com.example.javauniversityrest.controller;

import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.read.MentorReadDto;
import com.example.javauniversityrest.dto.read.StudentReadDto;
import com.example.javauniversityrest.dto.save.AdminSaveDto;
import com.example.javauniversityrest.dto.save.MentorSaveDto;
import com.example.javauniversityrest.dto.save.StudentSaveDto;
import com.example.javauniversityrest.service.AdminServiceImpl;
import com.example.javauniversityrest.service.MentorServiceImpl;
import com.example.javauniversityrest.service.StudentServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/mentors")
public class MentorController {

    private final MentorServiceImpl mentorService;

    @Autowired
    public MentorController(MentorServiceImpl mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public ResponseEntity<List<MentorReadDto>> showAdmins() {
        return new ResponseEntity<>(mentorService.getAllByModel(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MentorReadDto>> showAdmin(@PathVariable("id") Long id) {
        return new ResponseEntity<>(mentorService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addNewAdmin(@RequestBody MentorSaveDto mentorSaveDto) {
        mentorService.save(mentorSaveDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable Long id) {
        mentorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateAdmin(@RequestBody @NotNull MentorReadDto mentorReadDto, @PathVariable Long id) {
        mentorService.update(mentorReadDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
