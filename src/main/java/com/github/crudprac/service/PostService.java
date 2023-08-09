package com.github.crudprac.service;

import com.github.crudprac.dto.PostRequestDto;
import com.github.crudprac.dto.PostResponseDto;
import com.github.crudprac.repository.UserJpaRepository;
import com.github.crudprac.repository.entity.PostEntity;
import com.github.crudprac.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final UserJpaRepository userJpaRepository;
    public PostService(PostRepository postRepository, UserJpaRepository userJpaRepository) {
        this.postRepository = postRepository;
        this.userJpaRepository = userJpaRepository;
    }

    public PostResponseDto createPost(PostRequestDto postRequestDto){
        String title = postRequestDto.getTitle();
        String content = postRequestDto.getContent();
        String username = "test"; //로그인 후 유저네임 가져올 것
        PostEntity postEntity = new PostEntity(title, content, username);
        try{
            postRepository.save(postEntity);
            return new PostResponseDto(postEntity);
        } catch (RuntimeException exception){
            throw new NotAcceptableStatusException("save 에러");
        }
    }

    public List<PostResponseDto> findAll() {
        List<PostResponseDto> postList = postRepository.findAll().stream().map(PostResponseDto::new).collect(Collectors.toList());
        return postList;
    }

    public List<PostResponseDto> findByEmail(String author_email) {
        String username = "test";

        List<PostResponseDto> postList = postRepository.findByUsername(username).stream().map(PostResponseDto::new).collect(Collectors.toList());
        return postList;
    }
}
