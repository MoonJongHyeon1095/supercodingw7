package com.github.crudprac.dto.posts;

import com.github.crudprac.entity.Posts;
import com.github.crudprac.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PostsRequestDto {


    /**
     * 게시글의 등록과 수정을 처리할 요청(Request) 클래스
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Integer id;
        private String title;
        //        private String writer;
        private String content;
        private String createdDate, modifiedDate;
        //        private int view;
        private User user;

        /* Dto -> Entity */
        public Posts toEntity() {
            Posts posts = Posts.builder()
                    .id(id)
                    .title(title)
//                    .writer(writer)
                    .content(content)
//                    .view(0)
                    .user(user)
                    .build();

            return posts;
        }
    }
}