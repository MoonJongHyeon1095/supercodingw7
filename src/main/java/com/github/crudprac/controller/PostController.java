package com.github.crudprac.controller;

import com.github.crudprac.dto.MessageResponse;
import com.github.crudprac.dto.PostRequestDto;
import com.github.crudprac.dto.PostResponseDto;
import com.github.crudprac.exceptions.NotFoundException;
import com.github.crudprac.repository.UserJpaRepository;
import com.github.crudprac.repository.entity.UserEntity;
import com.github.crudprac.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserJpaRepository userJpaRepository;

    @PostMapping("/posts")
    public ResponseEntity<MessageResponse> createPost(@RequestBody PostRequestDto postRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return postService.createPost(postRequestDto, email);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> findPosts(){
        return postService.findAll();
    }

    @GetMapping("/posts/search")
    public List<PostResponseDto> findPostByEmail(
            @RequestParam String author_email) {

        UserEntity user = userJpaRepository.findByEmail(author_email)
                .orElseThrow(()->new NotFoundException("존재하지 않는 email입니다."));

        return postService.findByUserId(user.getId());
    }

    @PutMapping("/posts/{post_id}")
    public PostResponseDto updatePost(@PathVariable("post_id") Integer post_id, @RequestBody PostRequestDto postRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return postService.updatePost(email, post_id, postRequestDto);
    }

    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable("post_id") Integer post_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println(email);
        return postService.deletePost(email, post_id);
    }


}
