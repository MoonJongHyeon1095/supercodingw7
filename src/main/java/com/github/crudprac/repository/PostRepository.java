package com.github.crudprac.repository;

import com.github.crudprac.repository.entity.PostEntity;
import com.github.crudprac.repository.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findByUsername(String username);

    Collection<Object> findByUserId(int user_id);
}
