package com.github.crudprac.repository.entity;

import javax.persistence.*;

import com.github.crudprac.dto.PostRequestDto;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "posts") // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "posts") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of="post_id")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_id;
    @Column(name = "title", nullable = false, length = 20)
    private String title;
    @Column(name = "content", nullable = false, length = 100)
    private String content;
    @Column(name="username", length = 20, nullable = false)
    private String username;
    @Column(name="created_at", nullable = false)
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    public PostEntity(String title, String content, String username, UserEntity user) {

        this.title = title;
        this.content = content;
        this.username = username;
        this.user = user;
    }

    public PostEntity(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public void update(Integer post_id, PostRequestDto postRequestDto) {
        this.post_id = post_id;
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();

    }
}