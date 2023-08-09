package com.github.crudprac.controller;

import com.github.crudprac.config.LoginUser;
import com.github.crudprac.dto.comment.CommentRequestDto;
import com.github.crudprac.dto.comment.CommentResponseDto;
import com.github.crudprac.dto.CommentDto;
import com.github.crudprac.dto.user.UserDto;
import com.github.crudprac.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;

    /* CREATE */
    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<Long> save(@PathVariable Long id, @RequestBody CommentRequestDto dto,
                                     @LoginUser UserDto.Response userSessionDto) {
        return ResponseEntity.ok(commentService.commentSave(userSessionDto.getNickname(), id, dto));
    }

    /* READ */
    @GetMapping("/posts/{id}/comments")
    public List<CommentResponseDto> read(@PathVariable Long id) {
        return commentService.findAll(id);
    }

    /* UPDATE */
    @PutMapping({"/posts/{postsId}/comments/{id}"})
    public ResponseEntity<Long> update(@PathVariable Long postsId, @PathVariable Long id, @RequestBody CommentRequestDto dto) {
        commentService.update(postsId, id, dto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/posts/{postsId}/comments/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long postsId, @PathVariable Long id) {
        commentService.delete(postsId, id);
        return ResponseEntity.ok(id);
    }
}