package com.github.crudprac.repository.entity;

import com.github.crudprac.dto.comment.CommentsRequestDto;
import com.github.crudprac.exceptions.NotFoundException;
import com.github.crudprac.util.Time;
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
public class CommentsEntity extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Integer id;

    @Column(name = "author", nullable = false)
    private String userName;

    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

    @Column(name = "content", columnDefinition = "NVARCHAR(100)", nullable = false)
    private String content; // 댓글 내용

    @Column(name = "created_at")
    @CreatedDate
    private String created_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    public CommentsEntity(String userName, Integer likeCount, String content, UserEntity user, PostEntity post) {

        this.userName = userName;
        this.likeCount = likeCount;
        this.content = content;
        this.user = user;
        this.post = post;
    }

    public CommentsEntity(String userName, Integer likeCount, String content) {
        this.userName = userName;
        this.likeCount = likeCount;
        this.content = content;
    }


    public CommentsEntity(Integer id, String content, String userName, PostEntity post, String created_at) {
        this.id = id;
        this.content = content;
        this.userName = userName;
        this.post = post;
        this.created_at = created_at;
    }
}




