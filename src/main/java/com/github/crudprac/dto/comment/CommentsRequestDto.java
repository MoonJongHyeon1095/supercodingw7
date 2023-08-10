package com.github.crudprac.dto.comment;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class CommentsRequestDto {

    private Integer id;
    private Integer likeCount;
    private String content;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));


//
//    /* Dto -> Entity */
//    public Comments toEntity() {
//        Comments comments = Comments.builder()
//                .id(id)
//                .likeCount(likeCount)
//                .content(content)
//                .createdDate(createdDate)
////                .modifiedDate(modifiedDate)
//                .build();
//
//        return comments;
//    }

}