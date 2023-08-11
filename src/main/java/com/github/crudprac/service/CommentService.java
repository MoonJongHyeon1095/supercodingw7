package com.github.crudprac.service;

import com.github.crudprac.dto.comment.CommentsRequestDto;
import com.github.crudprac.dto.comment.CommentsResponseDto;
import com.github.crudprac.exceptions.NotFoundException;
import com.github.crudprac.repository.PostRepository;
import com.github.crudprac.repository.UserJpaRepository;
import com.github.crudprac.repository.entity.CommentsEntity;
import com.github.crudprac.repository.CommentsRepository;
import com.github.crudprac.repository.entity.PostEntity;
import com.github.crudprac.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentsRepository commentsRepository;
    private final PostRepository postRepository;
    private final UserJpaRepository userJpaRepository;

        public List<CommentsResponseDto> findComments() {
        List<CommentsEntity> commentsEntities = commentsRepository.findAll();
        return commentsEntities.stream().map(CommentsResponseDto::new).collect(Collectors.toList());
    }

//    public CommentsResponseDto createComments(CommentsRequestDto commentsRequestDto) {
//        String content = commentsRequestDto.getContent();
//        String userName = commentsRequestDto.getUserName();
//
//        PostEntity postEntity = PostEntity.builder()
//                .id(id)
//                .content(content)
//                .userName(userName)
//                .likeCount(likeCount)
//                .user(user)
//                .post(post)
//                .createdDate(createdDate)
//                .build();
//        return commentsRepository.saveComments(commentsEntity);
//    }

    public CommentsEntity createComments(CommentsRequestDto commentsRequestDto, String email) {
//            commentsRequestDto.getPostId();
        UserEntity user = userJpaRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("존재하지 않는 email입니다."));

        PostEntity post = postRepository.findById(commentsRequestDto.getPost_id()).orElseThrow(()->new NotFoundException("존재하지 않는 id입니다."));
        CommentsEntity commentsEntity = CommentsEntity.builder()
                .content(commentsRequestDto.getContent())
                .likeCount(0)
                .post(post)
                .userName(commentsRequestDto.getAuthor())
                .created_at(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")))
                .user(user)
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
        CommentsEntity commentsEntity = commentsRepository.findById(idInt).orElseThrow(()->new NotFoundException("존재하지 않는 id입니다."));

        String content = commentsRequestDto.getContent();
        commentsEntity.setContent(content);
         commentsRepository.findById(idInt);
//        CommentsEntity commentsEntityUpdated = commentsRepository.updateCommentsEntity(idInt, commentsEntity);
        return CommentsResponseDto.builder()
                .id(idInt)
                .content(content)
                .userName(commentsEntity.getUserName())
                .post(commentsEntity.getPost())
                .build();
    }

}






//@RequiredArgsConstructor
//@Service
//public class CommentService {
//
//    private final CommentRepository commentRepository;
//    private final UserRepository userRepository;
//    private final PostsRepository postsRepository;
//
//    /* CREATE */
//    // User와 Posts의 정보를 받아 Comment에 저장할 수 있게 set
//    @Transactional
//    public Integer save(Integer id, String nickname, CommentRequestDto dto) {
//        User user = userRepository.findByNickname(nickname);
//        Posts posts = postsRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + id));
//
//        dto.setUser(user);
//        dto.setPosts(posts);
//
//        Comments comment = dto.toEntity();
//        commentRepository.save(comment);
//
//        return dto.getId();
//    }
//
//    /* READ */
//    @Transactional(readOnly = true)
//    public List<CommentResponseDto> findAll(CommentRequestDto commentRequestDto) {
//
//        Posts posts = postsRepository.findById(commentRequestDto.getId()).orElseThrow(() ->
//                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: "));
//        List<Comments> comments = posts.getComments();
//        return comments.stream().map(CommentResponseDto::new).collect(Collectors.toList());
//    }
//
//    // comment 객체에 데이터를 가져와 영속화시키고, 데이터를 변경하여 트랜잭션 종료 시점에 커밋
//    /* UPDATE */
//    @Transactional
//    public void update(Integer postsId, CommentRequestDto dto) {
//
//        Comments comment = commentRepository.findByPostsIdAndId(postsId, postsId).orElseThrow(() ->
//                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + postsId));
//
//        comment.update(dto.getComment());
//    }
//
//    /* DELETE */
//    @Transactional
//    public void delete(Integer postsId) {
//
//        Comments comment = commentRepository.findByPostsIdAndId(postsId, postsId).orElseThrow(() ->
//                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + postsId));
//
//        commentRepository.delete(comment);
//    }
//
//    public Object commentSave(String nickname, Integer id, CommentRequestDto dto) {
//        return null;
//    }
//
//    public CommentResponseDto save(CommentRequestDto commentRequestDto) {
//        return null;
//    }
//}