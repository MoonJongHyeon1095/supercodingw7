package com.github.crudprac.repository;

import com.github.crudprac.repository.entity.SignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignJpaRepository extends JpaRepository<SignEntity, Integer> {

    Optional<SignEntity> findByEmail(String email);

    void deleteByEmail(String email);

    boolean existsByEmail(String email);
}
