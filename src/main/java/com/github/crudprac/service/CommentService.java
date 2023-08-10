package com.github.crudprac.service;

import com.github.crudprac.dto.comment.CommentsRequestDto;
import com.github.crudprac.dto.comment.CommentsResponseDto;
import com.github.crudprac.entity.CommentsEntity;
import com.github.crudprac.repository.CommentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private CommentsRepository commentsRepository;

    public CommentService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public List<CommentsResponseDto> findAllComments() {
        List<CommentsEntity> commentsEntities = commentsRepository.findAllComments();
        return commentsEntities.stream().map(CommentsResponseDto::new).collect(Collectors.toList());
    }

    public Integer savaItem(CommentsRequestDto commentsRequestDto) {
        CommentsEntity commentsEntity = new CommentsEntity(null, commentsRequestDto.getLikeCount(), commentsRequestDto.getContent(),
                commentsRequestDto.getCreatedDate());
        return commentsRepository.saveComments(commentsEntity);
    }

    public CommentsResponseDto findItemById(String id) {
        Integer idInt = Integer.parseInt(id);
        CommentsEntity commentsEntity = commentsRepository.findCommentsById(idInt);
        CommentsResponseDto comments = new CommentsResponseDto(commentsEntity);
        return comments;
    }

    public List<CommentsResponseDto> findCommentsByIds(List<String> ids) {
        List<CommentsEntity> commentsEntities = commentsRepository.findAllComments();
        return commentsEntities.stream()
                .map(CommentsResponseDto::new)
                .filter((comments -> ids.contains(comments.getId())))
                .collect(Collectors.toList());
    }

    public void deleteComments(String id) {
        Integer idInt = Integer.parseInt(id);
        commentsRepository.deleteComments(idInt);
    }

    public CommentsResponseDto updateComments(String id, CommentsRequestDto commentsRequestDto) {
        Integer idInt = Integer.valueOf(id);
        CommentsEntity commentsEntity = new CommentsEntity(idInt, commentsRequestDto.getLikeCount(),
                commentsRequestDto.getContent(), commentsRequestDto.getCreatedDate()
        );

        CommentsEntity commentsEntityUpdated = commentsRepository.updateCommentsEntity(idInt, commentsEntity);

        return new CommentsResponseDto(commentsEntityUpdated);
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