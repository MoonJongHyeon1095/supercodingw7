package com.github.crudprac.repository.entity;

import javax.persistence.*;

import lombok.*;

@Entity(name = "posts") // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "posts") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_id;
    @Column(name = "title", nullable = false, length = 20)
    private String title;
    @Column(name = "content", nullable = false, length = 100)
    private String content;
    @Column(name="username", length = 20, nullable = false)
    private String username;

    public PostEntity(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
}