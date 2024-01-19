package com.example.javauniversityrest.dto.save;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class RoleSaveDto {

    private String name;

    public RoleSaveDto(String name) {
        this.name = name;
    }
}
