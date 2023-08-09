package com.github.crudprac.controller;

import com.github.crudprac.dto.comment.CommentRequestDto;
import com.github.crudprac.dto.comment.CommentResponseDto;
import com.github.crudprac.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;

    /* CREATE */
    @PostMapping("/comments")
    public CommentResponseDto save(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.save(commentRequestDto);
    }

    /* READ */
    @GetMapping("/comments")
    public List<CommentResponseDto> read(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.findAll(commentRequestDto);
    }

    /* UPDATE */
    @PutMapping({"/comments/{comment_id}"})
    public ResponseEntity<Objects> update(@PathVariable Integer id, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.update(id, commentRequestDto);
        return (ResponseEntity<Objects>) ResponseEntity.ok();
    }

    /* DELETE */
    @DeleteMapping("/comments/{comment_id}")
    public ResponseEntity<Objects> delete(@PathVariable Integer id) {
        commentService.delete(id);
        return (ResponseEntity<Objects>) ResponseEntity.ok();
    }
}