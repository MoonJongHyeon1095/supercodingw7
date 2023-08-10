package com.github.crudprac.dto.comment;


import com.github.crudprac.entity.CommentsEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentsResponseDto {

    private Integer id;
    private Integer likeCount;
    private String content;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));


    public CommentsResponseDto() {
    }

    public CommentsResponseDto(CommentsRequestDto commentsRequestDto){
        this.id = commentsRequestDto.getId();
        this.likeCount = commentsRequestDto.getLikeCount();
        this.content = commentsRequestDto.getContent();
        this.createdDate = commentsRequestDto.getCreatedDate();

    }

    public CommentsResponseDto(Integer id, Integer likeCount, String content, String createdDate) {
        this.id = id;
        this.likeCount = likeCount;
        this.content = content;
        this.createdDate = createdDate;

    }

    public CommentsResponseDto(CommentsEntity commentsEntity){
        this.likeCount = commentsEntity.getLikeCount();
        this.content = commentsEntity.getContent();
        this.createdDate = commentsEntity.getCreatedDate();


    }

    public Integer getId() {
        return id;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedDate() {
        return createdDate;
    }

}