package com.github.crudprac.web.controller;

import com.github.crudprac.service.CommentService;
import com.github.crudprac.web.dto.UserSession;
import com.github.crudprac.web.dto.comment.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @GetMapping("/")                 /* default size = 10 */
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
    Pageable pageable) {
        model.addAttribute("posts", postsService.pageList(pageable));

        return "index";
    }

    /* CREATE */
    @PostMapping("/posts/{id}/comments")
    public ResponseEntity commentSave(@PathVariable Long id, @RequestBody CommentRequest dto,
                                      @LoginUser UserSession userSessionDto) {
        return ResponseEntity.ok(commentService.commentSave(userSessionDto.getNickname(), id, dto));
    }

    /* UPDATE */
    @PutMapping({"/posts/{postsId}/comments/{id}"})
    public ResponseEntity<Long> update(@PathVariable Long postsId, @PathVariable Long id, @RequestBody CommentDto.Request dto) {
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