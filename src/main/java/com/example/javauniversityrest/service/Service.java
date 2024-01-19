package com.example.javauniversityrest.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Service<M, R, S, V, K, P, U, Usdto, Urdto> { //M - model, R - readDto, S - saveDto, V - typeID, K - typeEmail
    void save(S saveDto, Class<M> clazz, Usdto userSaveDto, Class<U> uClass);
    List<R> getAllByModel(Class<R> clazz);
    Optional<R> findById(V id, Class<R> clazz);
    void update(R readDto, V id, Class<M> clazz, Urdto userReadDto, Class<U> uClass);
    void deleteById(V id);
    void deleteAll();
}
