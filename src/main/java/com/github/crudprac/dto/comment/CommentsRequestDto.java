package com.github.crudprac.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsRequestDto {

    private String content;
    private String author;
    private Integer post_id;

}