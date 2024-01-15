package com.example.javauniversityrest.dto.save;

import lombok.Data;

@Data
public class RoleSaveDto {

    private String name;

    public RoleSaveDto(String name) {
        this.name = name;
    }
}
