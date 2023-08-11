package com.github.crudprac.service;

import com.github.crudprac.dto.MessageResponse;
import com.github.crudprac.dto.PostRequestDto;
import com.github.crudprac.dto.PostResponseDto;
import com.github.crudprac.exceptions.DatabaseException;
import com.github.crudprac.exceptions.NotFoundException;
import com.github.crudprac.repository.UserJpaRepository;
import com.github.crudprac.repository.entity.PostEntity;
import com.github.crudprac.repository.PostRepository;
import com.github.crudprac.repository.entity.UserEntity;
import com.github.crudprac.util.JpaManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    @Transactional(transactionManager = "tmJpa")
    public ResponseEntity<MessageResponse> createPost(PostRequestDto postRequestDto, String email){
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

        PostEntity savedPost = JpaManager.managedSave(postRepository, postEntity);
        return ResponseEntity.ok(new MessageResponse("게시물이 성공적으로 작성되었습니다."));

    }

    public List<PostResponseDto> findAll() {
        List<PostResponseDto> postList = postRepository.findAll().stream().map(PostResponseDto::new).collect(Collectors.toList());
        return postList;
    }

    public List<PostResponseDto> findByUserId(int user_id) {
        List<PostEntity> posts = postRepository.findByUserId(user_id);
        return posts.stream()
                .map(PostResponseDto::new) // PostEntity를 PostResponseDto로 변환
                .collect(Collectors.toList());
    }

    @Transactional(transactionManager = "tmJpa")
    public PostResponseDto updatePost(String email, Integer post_id, PostRequestDto postRequestDto) {

        PostEntity post = postRepository.findById(post_id).orElseThrow(()->new NotFoundException("그런 게시글 없습니다."));

        if(!Objects.equals(email, post.getUsername())) throw new DatabaseException("너가 작성한 게시글이 아닙니다.");

        // 영속성 컨텍스트의 변경감지를 통해 update를 진행
        post.update(post_id, postRequestDto);
        PostEntity updatedPost = postRepository.save(post);
        return new PostResponseDto(updatedPost);

    }
}
