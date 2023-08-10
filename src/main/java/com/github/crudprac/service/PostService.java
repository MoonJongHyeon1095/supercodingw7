package com.github.crudprac.service;

import com.github.crudprac.dto.PostRequestDto;
import com.github.crudprac.dto.PostResponseDto;
import com.github.crudprac.exceptions.NotFoundException;
import com.github.crudprac.repository.UserJpaRepository;
import com.github.crudprac.repository.entity.PostEntity;
import com.github.crudprac.repository.PostRepository;
import com.github.crudprac.repository.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    public PostResponseDto createPost(PostRequestDto postRequestDto, String email){
        String title = postRequestDto.getTitle();
        String content = postRequestDto.getContent();
        String username = email;
        Optional<UserEntity> userOptional = userJpaRepository.findByEmail(email);
        UserEntity user = userOptional.orElseThrow(() -> new NotFoundException("존재하지 않는 email입니다."));

        PostEntity postEntity = PostEntity.builder()
                .title(title)
                .content(content)
                .username(username)
                .user(user)
                .created_at(LocalDateTime.now())
                .build();

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

    public List<PostResponseDto> findByUserId(int id) {
        return null;
    }

//    public List<PostResponseDto> findByEmail(String author_email) {
//        String username = "test";
//        postRepository.findByEmail(author_email)
//
//        List<PostResponseDto> postList = postRepository.findByUsername(username).stream().map(PostResponseDto::new).collect(Collectors.toList());
//        return postList;
//    }


//    public List<PostResponseDto> findByUserId(int user_id) {
//        List<PostResponseDto> postList = postRepository.findByUserId(user_id).stream().map(PostResponseDto::new).collect(Collectors.toList());
//        return postList;
//    }
}
