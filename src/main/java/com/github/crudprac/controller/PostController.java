package com.github.crudprac.controller;

import com.github.crudprac.dto.PostRequestDto;
import com.github.crudprac.dto.PostResponseDto;
import com.github.crudprac.exceptions.NotFoundException;
import com.github.crudprac.repository.UserJpaRepository;
import com.github.crudprac.repository.details.SignDetails;
import com.github.crudprac.repository.entity.UserEntity;
import com.github.crudprac.service.PostService;
import com.github.crudprac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserJpaRepository userJpaRepository;

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto){
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
            @RequestParam String author_email) throws IllegalAccessException {

        UserEntity user = userJpaRepository.findByEmail(author_email)
                .orElseThrow(()->new NotFoundException("존재하지 않는 email입니다."));

        return postService.findByUserId(user.getId());
    }
}
