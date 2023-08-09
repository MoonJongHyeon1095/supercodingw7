package com.github.crudprac.dto.comment;

import com.github.crudprac.entity.Comments;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentResponseDto {

    private Integer id;
    private String comment;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//    private String nickname;
    private Integer postsId;

    /* Entity -> Dto*/
    public CommentResponseDto(Comments comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
//        this.modifiedDate = comment.getModifiedDate();
//        this.nickname = comment.getUser().getNickname();
        this.postsId = comment.getPosts().getId();
    }


}