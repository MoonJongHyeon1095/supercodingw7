package com.github.crudprac.controller;

import com.github.crudprac.dto.PostRequestDto;
import com.github.crudprac.dto.PostResponseDto;
import com.github.crudprac.dto.ResponseMessageDto;
import com.github.crudprac.entity.Post;
import com.github.crudprac.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> findPosts(){
        return postService.findAll();
    }

    @GetMapping("/posts/search")
    public List<PostResponseDto> findPostByEmail(@RequestParam String author_email){
        return postService.findByEmail(author_email);
    }
}
