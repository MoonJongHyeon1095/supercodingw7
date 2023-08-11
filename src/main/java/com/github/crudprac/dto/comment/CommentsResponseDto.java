package com.github.crudprac.dto.comment;


import com.github.crudprac.repository.entity.CommentsEntity;
import com.github.crudprac.repository.entity.PostEntity;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsResponseDto {

    private Integer id;
    private String content;
    private String author;
    private PostEntity post;
    private String created_at;


    public CommentsEntity toEntity() {
        return new CommentsEntity(id, content, author, post, created_at);
    }

    public CommentsResponseDto(CommentsEntity commentsEntity) {
        this.id = commentsEntity.getId();
        this.content = commentsEntity.getContent();
        this.created_at = commentsEntity.getCreated_at();
        this.post = commentsEntity.getPost();
        this.author = commentsEntity.getAuthor();
    }
}