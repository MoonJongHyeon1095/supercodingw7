package com.github.crudprac.repository;

import com.github.crudprac.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Integer> {
    @Modifying
//    @Query("update Posts p set p.view = p.view + 1 where p.id = :id")
    int updateView(Integer id);

    Page<Posts> findByTitleContaining(String keyword, Pageable pageable);

    Optional<Posts> findById();
}