package com.github.crudprac.service;

import com.github.crudprac.dto.PostResponseDto;
import com.github.crudprac.dto.comment.CommentsRequestDto;
import com.github.crudprac.dto.comment.CommentsResponseDto;
import com.github.crudprac.exceptions.NotFoundException;
import com.github.crudprac.repository.PostRepository;
import com.github.crudprac.repository.UserJpaRepository;
import com.github.crudprac.repository.entity.CommentsEntity;
import com.github.crudprac.repository.CommentsRepository;
import com.github.crudprac.repository.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentsRepository commentsRepository;
    private final PostRepository postRepository;
    private final UserJpaRepository userJpaRepository;

//    public List<CommentsResponseDto> findComments() {
//        List<CommentsEntity> commentsEntities = commentsRepository.findAll();
//        return commentsEntities.stream().map(CommentsResponseDto::new).collect(Collectors.toList());
//    }

    public List<CommentsResponseDto> findAll() {
        List<CommentsResponseDto> comments = commentsRepository.findAll().stream().map(CommentsResponseDto::new).collect(Collectors.toList());
        return comments;
    }

    public CommentsEntity createComments(CommentsRequestDto commentsRequestDto) {

        PostEntity post = postRepository.findById(commentsRequestDto.getPost_id()).orElseThrow(() -> new NotFoundException("존재하지 않는 id입니다."));
        CommentsEntity commentsEntity = CommentsEntity.builder()
                .content(commentsRequestDto.getContent())
                .likeCount(0)
                .post(post)
                .author(commentsRequestDto.getAuthor())
                .created_at(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")))
                .user(post.getUser())
                .build();
        return commentsRepository.save(commentsEntity);
    }

    public void deleteComments(String id) {
        Integer idInt = Integer.parseInt(id);
        commentsRepository.deleteById(idInt);
    }

    @Transactional(transactionManager = "tmJpa")
    public CommentsResponseDto updateComments(String id, CommentsRequestDto commentsRequestDto) {
        Integer idInt = Integer.valueOf(id);
        CommentsEntity commentsEntity = commentsRepository.findById(idInt).orElseThrow(() -> new NotFoundException("존재하지 않는 id입니다."));

        String content = commentsRequestDto.getContent();
        commentsEntity.setContent(content);
        commentsRepository.findById(idInt);

        return CommentsResponseDto.builder()
                .id(idInt)
                .content(content)
                .author(commentsEntity.getAuthor())
                .post(commentsEntity.getPost())
                .build();
    }
}
