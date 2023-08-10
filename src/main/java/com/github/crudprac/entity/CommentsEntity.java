package com.github.crudprac.entity;

import com.github.crudprac.util.Time;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
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

    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

    @Column(name = "content",columnDefinition = "NVARCHAR(100)", nullable = false)
    private String content; // 댓글 내용

    @Column(name = "created_at")
    @CreatedDate
    private String createdDate;

    //    @Column(name = "modified_date")
    //    @LastModifiedDate
    //    private String modifiedDate;


    public CommentsEntity(int id, int likeCount, String content, Date createdAt) {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommentsEntity)) {
            return false;
        }

        CommentsEntity that = (CommentsEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

//    @Column(name = "modified_date")
//    @LastModifiedDate
//    private String modifiedDate;

