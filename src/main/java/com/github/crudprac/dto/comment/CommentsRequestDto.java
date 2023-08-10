package com.github.crudprac.dto.comment;

import com.github.crudprac.repository.entity.CommentsEntity;
import com.github.crudprac.repository.entity.PostEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsRequestDto {

    private String content;
    private String userName;
    private Integer postId;



}