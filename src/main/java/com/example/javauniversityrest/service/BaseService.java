package com.example.javauniversityrest.service;


import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<M extends ChangePassword, R, S extends ChangePassword, V, K, P> implements Service<M, R, S, V, K, P> {

    private final JpaRepository<M, V> repositoryDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MapperUtils mapperUtils;

    public BaseService(JpaRepository<M, V> repositoryDao, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils mapperUtils) {
        this.repositoryDao = repositoryDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public void save(S saveDto, Class<M> clazz) {
//        M model = (M) mapperUtils.mapToModelSave(saveDto, clazz);
//        model.setPassword("123");
//        model.getPassword();
//        final var modelPassword = getSet.getPassword(model);
//        final var encode = bCryptPasswordEncoder.encode(modelPassword);
//        getSet.setPassword(model, (P)encode);
//        repositoryDao.save(model);
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
//        final var oldPassword = getSet.getPassword((M)repositoryDao.findById(id));
//        M m = (M) mapperUtils.mapToModelRead(readDto, clazz);
//        if (!oldPassword.equals(getSet.getPassword(m))) {
//            getSet.setPassword(m, (P)bCryptPasswordEncoder.encode(getSet.getPassword(m)));}
//        repositoryDao.save(m);
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
