package com.example.javauniversityrest.config;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
//                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .requestMatchers("admins/**", "api/admins/**, api/**")
                                    .hasAnyRole( "ADMIN")
                                .requestMatchers( "mentors/**")
                                    .hasAnyRole("STUDENT", "MENTOR", "ADMIN")
                                .requestMatchers("students/**")
                                    .hasAnyRole("STUDENT", "MENTOR", "ADMIN")
                                .anyRequest().authenticated()

                )
                .logout(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .formLogin(login -> login.successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        final var roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

                        if (roles.contains("ROLE_ADMIN")) {
                            response.sendRedirect("/admins");
                        } else if (roles.contains("ROLE_MENTOR")) {
                            response.sendRedirect("/api/mentors");
                        } else if (roles.contains("ROLE_STUDENT")) {
                            response.sendRedirect("/api/students");
                        }
                    }}));
        return http.build();
        }
    }
