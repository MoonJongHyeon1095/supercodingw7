package com.github.crudprac.service;

import com.github.crudprac.dto.comment.CommentRequestDto;
import com.github.crudprac.dto.comment.CommentResponseDto;
import com.github.crudprac.entity.Comments;
import com.github.crudprac.entity.Posts;
import com.github.crudprac.entity.User;
import com.github.crudprac.repository.CommentRepository;
import com.github.crudprac.repository.PostsRepository;
import com.github.crudprac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    /* CREATE */
    // User와 Posts의 정보를 받아 Comment에 저장할 수 있게 set
    @Transactional
    public Integer save(Integer id, String nickname, CommentRequestDto dto) {
        User user = userRepository.findByNickname(nickname);
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + id));

        dto.setUser(user);
        dto.setPosts(posts);

        Comments comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();
    }

    /* READ */
    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAll() {
        Integer id = null;
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));
        List<Comments> comments = posts.getComments();
        return comments.stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    // comment 객체에 데이터를 가져와 영속화시키고, 데이터를 변경하여 트랜잭션 종료 시점에 커밋
    /* UPDATE */
    @Transactional
    public void update(Integer postsId, CommentRequestDto dto) {
        Integer id = null;
        Comments comment = commentRepository.findByPostsIdAndId(postsId, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));

        comment.update(dto.getComment());
    }

    /* DELETE */
    @Transactional
    public void delete(Integer postsId) {
        Integer id = null;
        Comments comment = commentRepository.findByPostsIdAndId(postsId, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));

        commentRepository.delete(comment);
    }

    public Object commentSave(String nickname, Integer id, CommentRequestDto dto) {
        return null;
    }

    public CommentResponseDto save(CommentRequestDto commentRequestDto) {
        return null;
    }
}