package com.example.javauniversityrest.dto.save;

import com.example.javauniversityrest.dto.read.RoleReadDto;
import com.example.javauniversityrest.model.Role;
import com.example.javauniversityrest.service.PersonGetSet;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
@Setter
public class UserSaveDto implements PersonGetSet<Role> {

    private String email;
    private Role role;
    private String password;
    private Long modelId;

    public UserSaveDto(String email, Role role, String password) {
        this.email = email;
        this.role = role;
        this.password = password;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public Long getModelId() {
        return null;
    }
}
