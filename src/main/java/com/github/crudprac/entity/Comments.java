package com.github.crudprac.entity;

import com.github.crudprac.util.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comments")
@Entity
public class Comments extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Integer id;

    @Column(name = "like_count", nullable = false)
    private Integer like;

    @Column(columnDefinition = "NVARCHAR(100)", nullable = false)
    private String comment; // 댓글 내용

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

//    @Column(name = "modified_date")
//    @LastModifiedDate
//    private String modifiedDate;

    // 댓글의 입장에선 게시글과 사용자는 다대일 관계이므로 @ManyToOne
    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 작성자

    /* 댓글 수정 */
    public void update(String comment) {
        this.comment = comment;
    }
}