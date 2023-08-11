package com.github.crudprac.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class CommentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Integer id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

    @Column(name = "content", length = 100, nullable = false)
    private String content; // 댓글 내용

    @Column(name = "created_at")
    @CreatedDate
    private String created_at;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    public CommentsEntity(String author, Integer likeCount, String content, UserEntity user, PostEntity post) {

        this.author = author;
        this.likeCount = likeCount;
        this.content = content;
        this.user = user;
        this.post = post;
    }

    public CommentsEntity(String author, Integer likeCount, String content) {
        this.author = author;
        this.likeCount = likeCount;
        this.content = content;
    }


    public CommentsEntity(Integer id, String content, String author, PostEntity post, String created_at) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.post = post;
        this.created_at = created_at;
    }

}




