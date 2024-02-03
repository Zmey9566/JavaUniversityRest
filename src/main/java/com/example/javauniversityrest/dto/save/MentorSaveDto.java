package com.example.javauniversityrest.dto.save;

import com.example.javauniversityrest.model.Role;
import com.example.javauniversityrest.service.PersonGetSet;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public class MentorSaveDto implements PersonGetSet<Role> {

    @Size(min = 2, max = 20, message = "Длина поляфамилия не должна быть менее 2 и более 20 символов")
    private String family;
    @NotEmpty(message = "поле Имя не должно быть пустым")
    private String name;
    private String email;
    private Role role;
//    private MentorStudent mentorStudent;
    private String password;

    public MentorSaveDto(String family, String name, String email, Role role, String password) {
        this.family = family;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    @Override
    public Long getId() {
        return null;
    }
}
