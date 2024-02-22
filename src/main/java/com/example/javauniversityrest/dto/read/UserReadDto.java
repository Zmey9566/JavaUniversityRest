package com.example.javauniversityrest.dto.read;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class UserReadDto {

    private Long id;
    private String email;
    private RoleReadDto role;
    private String password;
    private Long modelId;

    public UserReadDto(String email, RoleReadDto role, String password) {
        this.email = email;
        this.role = role;
        this.password = password;
    }
}
