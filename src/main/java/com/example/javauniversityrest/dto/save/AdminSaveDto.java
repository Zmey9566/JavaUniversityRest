package com.example.javauniversityrest.dto.save;

import com.example.javauniversityrest.service.GetSet;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public class AdminSaveDto {

    @Size(min = 2, max = 20, message = "Длина поляфамилия не должна быть менее 2 и более 20 символов")
    private String family;

    @NotEmpty(message = "поле Имя не должно быть пустым")
    private String name;

    private String email;
    private RoleSaveDto role;
    private String password;

    public AdminSaveDto(String family, String name, String email, RoleSaveDto role,String password) {
        this.family = family;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Bean
    GetSet<AdminSaveDto, String> getSet() {
        return new GetSet<AdminSaveDto, String>() {
            @Override
            public String getPassword(AdminSaveDto model) {
                return model.getPassword();
            }

            @Override
            public void setPassword(AdminSaveDto model, String password) {
                model.setPassword(password);
            }
        };
    }
}

