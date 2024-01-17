package com.example.javauniversityrest.dto.read;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class AdminReadDto {

    private Long id;
    @Size(min = 2, max = 20, message = "Длина поля фамилия не должна быть менее 2 и более 20 символов")
    private String family;
    @NotEmpty(message = "поле Имя не должно быть пустым")
    private String name;
    private String email;
    private RoleReadDto role;
    private String password;

    public AdminReadDto(String family, String name, String email, RoleReadDto role, String password) {
        this.family = family;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }
}
