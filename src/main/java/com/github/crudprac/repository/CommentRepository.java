package com.github.crudprac.repository;

import com.github.crudprac.entity.Comments;
import com.github.crudprac.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comments, Integer> {
    /* 게시글 댓글 목록 가져오기 */
    List<Comments> getCommentByPostsOrderById(Posts posts);

    Optional<Comments> findByPostsIdAndId(Integer postsId, Integer id);
}