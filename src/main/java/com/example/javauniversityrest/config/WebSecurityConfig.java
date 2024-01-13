package com.example.javauniversityrest.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("forAdmin/**")
                                    .hasRole("ADMIN")
                                .requestMatchers("forMentor")
                                    .hasRole("MENTOR")
                                .requestMatchers("forStudent")
                                    .hasRole("STUDENT")
                                .anyRequest().authenticated()
                )
                .logout(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .formLogin(login -> login.successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException,
                                                        ServletException {
                        final var role = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

                        if (role.contains("ROLE_ADMIN")) {
                            response.sendRedirect("forAdmin");
                        } else if (role.contains("ROLE_MENTOR")) {
                            response.sendRedirect("forMentor");
                        } else if (role.contains("ROLE_STUDENT")) {
                            response.sendRedirect("forStudent");
                        }
                    }
                }));
        return http.build();
    }
}
