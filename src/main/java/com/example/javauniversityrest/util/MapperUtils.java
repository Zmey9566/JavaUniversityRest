package com.example.javauniversityrest.util;

import com.example.javauniversityrest.dto.read.AdminReadDto;
import com.example.javauniversityrest.dto.read.LessonReadDto;
import com.example.javauniversityrest.dto.read.MentorReadDto;
import com.example.javauniversityrest.dto.read.StudentReadDto;
import com.example.javauniversityrest.dto.save.AdminSaveDto;
import com.example.javauniversityrest.dto.save.LessonSaveDto;
import com.example.javauniversityrest.dto.save.MentorSaveDto;
import com.example.javauniversityrest.dto.save.StudentSaveDto;
import com.example.javauniversityrest.model.Admin;
import com.example.javauniversityrest.model.Lesson;
import com.example.javauniversityrest.model.Mentor;
import com.example.javauniversityrest.model.Student;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperUtils<M, R, S> {

    ModelMapper modelMapper = new ModelMapper();

    public R mapToModelReadDto (M m, Class<? extends R> R) {return modelMapper.map(m, R);}
    public S mapToModelSaveDto (M m, Class<? extends S> S) {return modelMapper.map(m, S);}
    public M mapToModelRead (R r, Class<? extends M> M) {return modelMapper.map(r, M);}
    public M mapToModelSave (S s, Class<? extends M> M) {return modelMapper.map(s, M);}

    /**
     * Mapper from Admin to AdminDto
     */

    public AdminReadDto mapToAdminReadDto (Admin admin) {
        return modelMapper.map(admin, AdminReadDto.class);
    }
    public AdminSaveDto mapToAdminSaveDto (Admin admin) {
        return modelMapper.map(admin, AdminSaveDto.class);
    }

    /**
     * Mapper from AdminDto to Admin
     */

    public Admin mapToAdminRead (AdminReadDto adminReadDto){
        return modelMapper.map(adminReadDto, Admin.class);
    }
    public Admin mapToAdminSave (AdminSaveDto adminSaveDto){
        return modelMapper.map(adminSaveDto, Admin.class);
    }

    /**
     * Mapper from Mentor to MentorDto
     */

    public MentorReadDto mapToMentorReadDto (Mentor mentor) {
        return modelMapper.map(mentor, MentorReadDto.class);
    }
    public MentorSaveDto mapToMentorSaveDto (Mentor mentor) {
        return modelMapper.map(mentor, MentorSaveDto.class);
    }

    /**
     * Mapper from MentorDto to Mentor
     */

    public Mentor mapToMentorRead (MentorReadDto mentorReadDto){
        return modelMapper.map(mentorReadDto, Mentor.class);
    }
    public Mentor mapToMentorSave (MentorSaveDto mentorSaveDto){
        return modelMapper.map(mentorSaveDto, Mentor.class);
    }

    /**
     * Mapper from Student to StudentDto
     */

    public StudentReadDto mapToStudentReadDto (Student student) {
        return modelMapper.map(student, StudentReadDto.class);
    }
    public StudentSaveDto mapToStudentSaveDto (Student student) {
        return modelMapper.map(student, StudentSaveDto.class);
    }

    /**
     * Mapper from StudentDto to Student
     */

    public Student mapToStudentRead (StudentReadDto studentReadDto){
        return modelMapper.map(studentReadDto, Student.class);
    }
    public Student mapToStudentSave (StudentSaveDto studentSaveDto){
        return modelMapper.map(studentSaveDto, Student.class);
    }

    /**
     * Mapper from Lesson to LessonDto
     */

    public LessonReadDto mapToLessonReadDto (Lesson lesson) {
        return modelMapper.map(lesson, LessonReadDto.class);
    }
    public LessonSaveDto mapToLessonSaveDto (Lesson lesson) {
        return modelMapper.map(lesson, LessonSaveDto.class);
    }

    /**
     * Mapper from LessonDto to Lesson
     */

    public Lesson mapToLessonRead (LessonReadDto lessonReadDto){
        return modelMapper.map(lessonReadDto, Lesson.class);
    }
    public Lesson mapToLessonSave (LessonSaveDto lessonSaveDto){
        return modelMapper.map(lessonSaveDto, Lesson.class);
    }
}
