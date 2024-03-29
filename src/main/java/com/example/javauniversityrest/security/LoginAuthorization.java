package com.example.javauniversityrest.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginAuthorization extends UserDetailsService {

    UserDetails loadUserByUserName(String username) throws UsernameNotFoundException;
}
