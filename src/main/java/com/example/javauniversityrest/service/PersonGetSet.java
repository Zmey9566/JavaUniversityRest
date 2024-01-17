package com.example.javauniversityrest.service;

public interface PersonGetSet<T> {
    String getPassword();
    void setPassword(String password);
//    String getFamily();
//    void setFamily(String family);
//    String getName();
//    void setName(String name);
    String getEmail();
    void setEmail(String email);
    T getRole();
    void setRole(T role);
}
