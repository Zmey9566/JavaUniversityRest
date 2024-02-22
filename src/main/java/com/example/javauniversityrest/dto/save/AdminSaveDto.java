package com.example.javauniversityrest.dto.save;

import com.example.javauniversityrest.dto.read.RoleReadDto;
import com.example.javauniversityrest.model.Role;
import com.example.javauniversityrest.service.PersonGetSet;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Data
@Component
public class AdminSaveDto implements PersonGetSet<Role> {

    @Size(min = 2, max = 20, message = "Длина поляфамилия не должна быть менее 2 и более 20 символов")
    private String family;

    @NotEmpty(message = "поле Имя не должно быть пустым")
    private String name;

    private String email;
    private Role role;
    private String password;

    public AdminSaveDto(String family, String name, String email, Role role, String password) {
        this.family = family;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public Long getModelId() {
        return null;
    }

    @Override
    public void setModelId(Long modelId) {

    }
}

