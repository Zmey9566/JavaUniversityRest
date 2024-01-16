package com.example.javauniversityrest.service;


import com.example.javauniversityrest.dao.AdminDao;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class Repository<M, R, S, V, K, P> implements Service<M, R, S, V, K, P>{

    private final JpaRepository<M, V> repositoryDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MapperUtils mapperUtils;
    private final GetSet<M, P> getSet;

    public Repository(JpaRepository<M, V> repositoryDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils mapperUtils, GetSet<M, P> getSet) {
        this.repositoryDao = repositoryDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapperUtils = mapperUtils;
        this.getSet = getSet;
    }

    @Override
    public void save(S saveDto, Class<M> clazz) {
        M model = (M) mapperUtils.mapToModelSave(saveDto, clazz);
        final var modelPassword = getSet.getPassword(model);
        final var encode = bCryptPasswordEncoder.encode(modelPassword);
        getSet.setPassword(model, (P)encode);
        repositoryDao.save(model);
    }

    @Override
    public List<R> getAllByModel(Class<R> clazz) {
        List<M> models = repositoryDao.findAll();
        return (List<R>) models.stream()
                .map(m -> mapperUtils.mapToModelReadDto(m, clazz))
                .toList();
    }

    @Override
    public Optional<R> findById(V id, Class<R> clazz) {
        return (Optional<R>) repositoryDao.findById(id)
                .map(m -> Optional.of(mapperUtils.mapToModelReadDto(m, clazz)))
                .orElse(null);
    }

    @Override
    public void update(R readDto, V id, Class<M> clazz) {
        final var oldPassword = getSet.getPassword((M)repositoryDao.findById(id));
        M m = (M) mapperUtils.mapToModelRead(readDto, clazz);
        if (!oldPassword.equals(getSet.getPassword(m))) {
            getSet.setPassword(m, (P)bCryptPasswordEncoder.encode(getSet.getPassword(m)));}
        repositoryDao.save(m);
    }

    @Override
    public void deleteById(V id) {
        repositoryDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repositoryDao.deleteAll();
    }

}
