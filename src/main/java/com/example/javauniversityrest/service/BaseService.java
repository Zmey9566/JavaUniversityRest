package com.example.javauniversityrest.service;


import com.example.javauniversityrest.model.Role;
import com.example.javauniversityrest.util.MapperUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseService<M extends PersonGetSet, R, S extends PersonGetSet, V, K, P, U extends PersonGetSet, Usdto, Urdto> implements Service<M, R, S, V, K, P, U, Usdto, Urdto>{

    private final JpaRepository<M, V> repositoryDao;
    private final JpaRepository<U, V> uRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MapperUtils mapperUtils;

    public BaseService(JpaRepository<M, V> repositoryDao, JpaRepository<U, V> uRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MapperUtils mapperUtils) {
        this.repositoryDao = repositoryDao;
        this.uRepository = uRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapperUtils = mapperUtils;
    }
//    Role role = new Role(1, "ROLE_ADMIN");
    @Override
    public void save(S saveDto, Class<M> clazz, Usdto userSaveDto, Class<U> uClass) {
        final var model = (M) mapperUtils.mapToModelSave(saveDto, clazz); // Преобразовываем МодельДТО в модель
        final var password = model.getPassword(); // Получаем пароль модели
        final var encode = bCryptPasswordEncoder.encode(password); // Шифруем пароль модели
        model.setPassword(encode); // Устанавливаем зашифрованный пароль для модели
        repositoryDao.save(model); // Сохраняем модель
        U user = (U) mapperUtils.mapToModelSave(userSaveDto, uClass); //Преобразовываем ЮзерДТО в Юзера
        // Для последующей корректой работы SpringSecurity копируем данные, необходимые для авторизации в системе созданного пользователя в сущность User
        user.setEmail(model.getEmail());
        user.setPassword(encode);
        user.setRole(model.getRole());
        uRepository.save(user); //Сохраняем Юзера
    }

    @Override
    public List<R> getAllByModel(Class<R> clazz) {
        List<M> models = repositoryDao.findAll();
        return (List<R>) models.stream()
                .sorted(Comparator.comparingLong(PersonGetSet::getId))
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
    public void update(R readDto, V id, Class<M> clazz, Urdto userReadDto, Class<U> uClass) {
        final var oldPassword = repositoryDao.findById(id).get().getPassword();
        final var model = (M) mapperUtils.mapToModelRead(readDto, clazz);
        final var modelPassword = model.getPassword();

        //Проверяем изменился ли пароль пользователя, в случае изменения, шифруем новый пароль
        if (!oldPassword.equals(modelPassword)) {
            model.setPassword(bCryptPasswordEncoder.encode(modelPassword));
        }
        repositoryDao.save(model); // Сохраняем модель

        U user = (U) mapperUtils.mapToModelSave(userReadDto, uClass); //Преобразовываем ЮзерДТО в Юзера

        // Обновляем данные юзера
        user.setEmail(model.getEmail());
        user.setPassword(model.getPassword());
        user.setRole(model.getRole());
        uRepository.save(user);
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
