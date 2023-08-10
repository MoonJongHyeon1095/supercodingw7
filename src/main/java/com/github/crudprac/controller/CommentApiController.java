package com.github.crudprac.controller;

import com.github.crudprac.dto.comment.CommentsRequestDto;
import com.github.crudprac.dto.comment.CommentsResponseDto;
import com.github.crudprac.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;

    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    /* CREATE */
    @PostMapping("/comments")
    public String registerComments(@RequestBody CommentsRequestDto commentsRequestDto) {
        commentService.savaItem(commentsRequestDto);
        return "댓글이 성공적으로 작성되었습니다.";
    }

    /* READ */

    @GetMapping("/comments")
    public List<CommentsResponseDto> findAllComments() {
        return commentService.findAllComments();
    }

    /* UPDATE */
    @PutMapping("/comments/{id}")
    public String updateComments(@PathVariable String id, @RequestBody CommentsRequestDto commentsRequestDto) {
        commentService.updateComments(id, commentsRequestDto);
        return "댓글이 성공적으로 수정되었습니다.";
    }

    /* DELETE */
    @DeleteMapping("/comments/{id}")
    public String deleteCommentsByPathId(@PathVariable String id) {
        commentService.deleteComments(id);
        return "Object with id =" + id + "has been deleted";
    }

}