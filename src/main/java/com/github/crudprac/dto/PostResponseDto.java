package com.github.crudprac.dto;

import com.github.crudprac.repository.entity.PostEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Integer post_id;
    private String title;
    private String contents;
    private String author;
    private LocalDateTime created_at;

    public PostResponseDto(PostEntity postEntity) {
        this.post_id = postEntity.getPost_id();
        this.title = postEntity.getTitle();
        this.contents = postEntity.getContent();
        this.author = postEntity.getUsername();
    }
}
