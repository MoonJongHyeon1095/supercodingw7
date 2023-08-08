package com.github.crudprac.web.dto;

import com.github.crudprac.entity.Posts;
import com.github.crudprac.web.dto.comment.CommentResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시글 정보를 리턴할 응답(Response) 클래스
 * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
 * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
 */

@Getter
public class PostsResponse {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private String createdDate, modifiedDate;
    private int view;
    private Long userId;
    private List<CommentResponse> comments;

    /* Entity -> Dto*/
    public PostsResponse(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.writer = posts.getWriter();
        this.content = posts.getContent();
        this.createdDate = posts.getCreatedDate();
        this.modifiedDate = posts.getModifiedDate();
        this.view = posts.getView();
        this.userId = posts.getUser().getId();
        this.comments = posts.getComments().stream().map(CommentResponse::new).collect(Collectors.toList());
    }
}