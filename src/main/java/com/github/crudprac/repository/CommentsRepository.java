package com.github.crudprac.repository;

import com.github.crudprac.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//public interface CommentRepository extends JpaRepository<Comments, Integer> {
//    /* 게시글 댓글 목록 가져오기 */
//    List<Comments> getCommentByPostsOrderById(Posts posts);
//
//    Optional<Comments> findByPostsIdAndId(Integer postsId, Integer id);
//}

public interface CommentsRepository {

    List<CommentsEntity> findAllComments();

    Integer saveComments(CommentsEntity commentsEntity);

    CommentsEntity updateCommentsEntity(Integer idInt, CommentsEntity commentsEntity);

    void deleteComments(int parseInt);

    CommentsEntity findCommentsById(Integer idInt);
    void updateCommentsStock(Integer commentsId, Integer i);


    void updateCommentsStock(Integer commentsId, String content);
}