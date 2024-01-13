package com.example.javauniversityrest.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Service<M, R, S, V> { //M - model, R - readDto, S - saveDto, V - typeID
    void save(M model, S saveDto);
    List<R> getAll(Class<R> clazz);
    Optional<R> findById(V id, Class<R> clazz);
    void update(R readDto, V id, Class<M> clazz);
    void deleteById(V id);
    void deleteAll();
}
