package com.github.crudprac.dto.comment;


import com.github.crudprac.repository.entity.CommentsEntity;
import com.github.crudprac.repository.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsResponseDto {

    private Integer id;
    private String content;
    private String userName;
    private PostEntity post;
    private String created_at;


    public CommentsEntity toEntity() {
        return new CommentsEntity(id, content, userName, post, created_at);
    }
//    public CommentsResponseDto() {
//    }
//
//    public CommentsResponseDto(CommentsRequestDto commentsRequestDto) {
//        this.content = commentsRequestDto.getContent();
//        this.userName = commentsRequestDto.getUserName();
//        this.post = commentsRequestDto.getPost().getPost_id();
//
//    }

//    public CommentsResponseDto(Integer id, Integer likeCount, String content, String createdDate,
//                               Integer user, Integer post, String userName) {
//        this.id = id;
//        this.likeCount = likeCount;
//        this.content = content;
//        this.createdDate = createdDate;
//        this.user = user;
//        this.post = post;
//        this.userName = userName;
//    }

    public CommentsResponseDto(CommentsEntity commentsEntity) {
        this.id = commentsEntity.getId();
//        this.likeCount = commentsEntity.getLikeCount();
        this.content = commentsEntity.getContent();
        this.created_at = commentsEntity.getCreated_at();
//        this.user = commentsEntity.getUser().getId();
        this.post = commentsEntity.getPost();
        this.userName = commentsEntity.getUserName();
    }


}