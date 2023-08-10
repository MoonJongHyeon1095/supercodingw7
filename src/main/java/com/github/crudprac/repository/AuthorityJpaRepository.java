package com.github.crudprac.repository;

import com.github.crudprac.repository.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityJpaRepository extends JpaRepository<AuthorityEntity, Integer> {

}
