package com.github.crudprac.entity;

import javax.persistence.*;

import com.github.crudprac.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "posts") // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
//@Table(name = "posts") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;
    @Column(name = "title", nullable = false, length = 20)
    private String title;
    @Column(name = "content", nullable = false, length = 100)
    private String content;
    @Column(name="username", length = 20, nullable = false)
    private String username;



    public Post(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
}