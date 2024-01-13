package com.example.javauniversityrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryDao<M, V> extends JpaRepository<M, V> {
}
