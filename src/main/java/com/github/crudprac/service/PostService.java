package com.github.crudprac.service;

import com.github.crudprac.dto.PostRequestDto;
import com.github.crudprac.dto.PostResponseDto;
import com.github.crudprac.entity.Post;
import com.github.crudprac.entity.User;
import com.github.crudprac.repository.users.PostRepository;
import com.github.crudprac.repository.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto postRequestDto){
        String title = postRequestDto.getTitle();
        String content = postRequestDto.getContent();
        String username = "test"; //로그인 후 유저네임 가져올 것

        Post post = new Post(title, content, username);
        postRepository.save(post);
        return new PostResponseDto(post);
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
