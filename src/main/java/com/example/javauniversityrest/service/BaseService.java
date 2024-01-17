package com.example.javauniversityrest.service;


import com.example.javauniversityrest.dao.UserDao;
import com.example.javauniversityrest.model.Role;
import com.example.javauniversityrest.model.User;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<M extends PersonGetSet, R, S extends PersonGetSet, V, K, P, U extends PersonGetSet, Usdto> implements Service<M, R, S, V, K, P, U, Usdto>{

    private final JpaRepository<M, V> repositoryDao;
    private final JpaRepository<U, V> repositoryDao1;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MapperUtils mapperUtils;

    public BaseService(JpaRepository<M, V> repositoryDao, JpaRepository<U, V> repositoryDao1, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils mapperUtils) {
        this.repositoryDao = repositoryDao;
        this.repositoryDao1 = repositoryDao1;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public void save(S saveDto, Class<M> clazz, Usdto userSaveDto, Class<U> uClass) {
        M model = (M) mapperUtils.mapToModelSave(saveDto, clazz); // Преобразуем ДТО в модель
        final var password = model.getPassword(); // Получаем пароль модели
        final var encode = bCryptPasswordEncoder.encode(password); // Шифруем пароль модели
        model.setPassword(encode); // Устанавливаем зашифрованный пароль для модели
        repositoryDao.save(model); // Сохраняем модель
        U user = (U) mapperUtils.mapToModelSave(userSaveDto, uClass);
        user.setEmail(model.getEmail());
        user.setPassword(encode);
        user.setRole(model.getRole());
        repositoryDao1.save(user);

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
        final var oldPassword = repositoryDao.findById(id).get().getPassword();
        M m = (M) mapperUtils.mapToModelRead(readDto, clazz);
        if (!oldPassword.equals(m.getPassword())) {
            m.setPassword(bCryptPasswordEncoder.encode(m.getPassword()));
        }
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
