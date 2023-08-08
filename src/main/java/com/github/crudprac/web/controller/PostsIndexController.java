package com.github.crudprac.web.controller;

import com.github.crudprac.web.dto.UserSession;
import com.github.crudprac.web.dto.comment.CommentResponse;
import com.github.crudprac.web.dto.PostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 화면 연결 Controller
 */
@Controller
@RequiredArgsConstructor
public class PostsIndexController {

    private final PostsService postsService;


    /* 글 상세보기 */
    @GetMapping("/posts/read/{id}")
    public String read(@PathVariable Long id, @LoginUser UserSession user, Model model) {
        PostsResponse dto = postsService.findById(id);
        List<CommentResponse> comments = dto.getComments();

        /* 댓글 관련 */
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }

        /* 사용자 관련 */
        if (user != null) {
            model.addAttribute("user", user.getNickname());

            /*게시글 작성자 본인인지 확인*/
            if (dto.getUserId().equals(user.getId())) {
                model.addAttribute("writer", true);
            }
        }
        postsService.updateView(id); // views ++
        model.addAttribute("posts", dto);
        return "posts/posts-read";
    }

    /* 글 상세보기 */
    @GetMapping("/posts/read/{id}")
    public String read(@PathVariable Long id, @LoginUser UserSession user, Model model) {
        PostsResponse dto = postsService.findById(id);
        List<CommentResponse> comments = dto.getComments();

        /* 댓글 리스트 */
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }
        /* 사용자 관련 */
        if (user != null) {
            model.addAttribute("user", user);

            /* 게시글 작성자 본인인지 확인 */
            if (dto.getUserId().equals(user.getId())) {
                model.addAttribute("writer", true);
            }
            /* 댓글 작성자 본인인지 확인 */
            for (int i = 0; i < comments.size(); i++) {
                //댓글 작성자 id와 현재 사용자 id를 비교해 true/false 판단
                boolean isWriter = comments.get(i).getUserId().equals(user.getId());
                log.info("isWriter? : " + isWriter);
                model.addAttribute("isWriter", isWriter);
            }
        }
        postsService.updateView(id); // views ++
        model.addAttribute("posts", dto);
        return "posts/posts-read";
    }

}