package com.github.crudprac.web.dto.comment;

import com.github.crudprac.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponse {

    private Long id;
    private String comment;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String nickname;
    private Long postsId;

    /* Entity -> Dto*/
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
        this.nickname = comment.getUser().getNickname();
        this.postsId = comment.getPosts().getId();
    }
}