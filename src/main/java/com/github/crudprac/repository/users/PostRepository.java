package com.github.crudprac.repository.users;

import com.github.crudprac.dto.PostResponseDto;
import com.github.crudprac.entity.Post;
import com.github.crudprac.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUsername(String username);
}
