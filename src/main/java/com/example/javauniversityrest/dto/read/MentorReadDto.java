package com.example.javauniversityrest.dto.read;

import com.example.javauniversityrest.model.MentorStudent;
import com.example.javauniversityrest.model.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MentorReadDto {

    private Long id;
    @Size(min = 2, max = 20, message = "Длина поляфамилия не должна быть менее 2 и более 20 символов")
    private String family;
    @NotEmpty(message = "поле Имя не должно быть пустым")
    private String name;
    private String email;
    private Role role;
    private MentorStudent mentorStudent;
    private String password;
}
