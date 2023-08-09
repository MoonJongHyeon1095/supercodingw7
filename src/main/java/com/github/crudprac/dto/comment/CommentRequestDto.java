package com.github.crudprac.dto.comment;

import com.github.crudprac.entity.Comments;
import com.github.crudprac.entity.Posts;
import com.github.crudprac.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class CommentRequestDto {
    private Integer id;
    private String comment;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private User user;
    private Posts posts;

    /* Dto -> Entity */
    public Comments toEntity() {
        Comments comments = Comments.builder()
                .id(id)
                .comment(comment)
                .createdDate(createdDate)
//                .modifiedDate(modifiedDate)
                .user(user)
                .posts(posts)
                .build();

        return comments;
    }


    public void setUser(User user) {
    }

    public void setPosts(Posts posts) {
    }

    public Integer getId() {
        return null;
    }

    public String getComment() {
        return null;
    }
}