package com.example.javauniversityrest.dto.save;

import com.example.javauniversityrest.service.PersonGetSet;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public class UserSaveDto implements PersonGetSet<RoleSaveDto> {

    private String email;
    private RoleSaveDto role;
    private String password;

    public UserSaveDto(String email, RoleSaveDto role, String password) {
        this.email = email;
        this.role = role;
        this.password = password;
    }

    @Override
    public Long getId() {
        return null;
    }
}
