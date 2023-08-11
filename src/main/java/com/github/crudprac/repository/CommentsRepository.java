package com.github.crudprac.repository;

import com.github.crudprac.repository.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity, Integer>{

}