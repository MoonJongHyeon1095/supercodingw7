package com.github.crudprac.dto;

import com.github.crudprac.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class PostResponseDto {
    private Long post_id;
    private String title;
    private String contents;
    private String author;
    private LocalDateTime created_at;

    public PostResponseDto(Post post) {
        this.post_id = post.getPost_id();
        this.title = post.getTitle();
        this.contents = post.getContent();
        this.author = post.getUsername();
    }
}
