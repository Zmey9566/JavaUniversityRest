package com.example.javauniversityrest.dto.read;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class RoleReadDto {

    private Long id;
    private String name;

    public RoleReadDto(String name) {
        this.name = name;
    }
}
