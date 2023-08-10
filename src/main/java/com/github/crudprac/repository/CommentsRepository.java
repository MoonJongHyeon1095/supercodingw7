package com.github.crudprac.repository;

import com.github.crudprac.repository.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//public interface CommentRepository extends JpaRepository<Comments, Integer> {
//    /* 게시글 댓글 목록 가져오기 */
//    List<Comments> getCommentByPostsOrderById(Posts posts);
//
//    Optional<Comments> findByPostsIdAndId(Integer postsId, Integer id);
//}

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity, Integer>{

//    List<CommentsEntity> fin();
//    CommentsEntity createComments(CommentsEntity commentsEntity);

//    CommentsEntity up(Integer idInt, CommentsEntity commentsEntity);

//    void (int parseInt);

//    CommentsEntity findCommentsById(Integer idInt);

}