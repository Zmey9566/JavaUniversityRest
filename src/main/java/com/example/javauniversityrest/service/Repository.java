package com.example.javauniversityrest.service;


import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;


public abstract class Repository<M, R, S, V> implements Service<M, R, S, V> {

    private final JpaRepository<M, V> repositoryDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MapperUtils mapperUtils;

    public Repository(JpaRepository<M, V> repositoryDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils mapperUtils) {
        this.repositoryDao = repositoryDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public void save(M model, S saveDto) {
//        model = (M) mapperUtils.mapToModelSave(saveDto, model.getClass());
        repositoryDao.save(model);
    }

    @Override
    public List<R> getAll(Class<R> clazz) {
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
        M m = (M) mapperUtils.mapToModelRead(readDto, clazz);
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
