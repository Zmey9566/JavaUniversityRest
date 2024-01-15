package com.example.javauniversityrest.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Service<M, R, S, V, K> { //M - model, R - readDto, S - saveDto, V - typeID, K - typeEmail
    void save(S saveDto, Class<M> clazz);
    List<R> getAllByModel(Class<R> clazz);
    Optional<R> findById(V id, Class<R> clazz);
    Optional<R> findByEmail(K email, Class<R> clazz);
    void update(R readDto, V id, Class<M> clazz);
    void deleteById(V id);
    void deleteAll();
}
