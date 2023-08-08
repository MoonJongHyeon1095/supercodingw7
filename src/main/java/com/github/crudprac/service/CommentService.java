package com.github.crudprac.service;

import com.github.crudprac.entity.Comment;
import com.github.crudprac.entity.Posts;
import com.github.crudprac.repository.CommentRepository;
import com.github.crudprac.repository.PostsRepository;
import com.github.crudprac.repository.UserRepository;
import com.github.crudprac.web.dto.User;
import com.github.crudprac.web.dto.comment.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    /* Views Counting */
    @Transactional
    public int updateView(Long id) {
        return postsRepository.updateView(id);
    }

    /* Paging */
    @Transactional(readOnly = true)
    public Page<Posts> pageList(Pageable pageable) {
        return postsRepository.findAll(pageable);
    }

    /* CREATE */
    @Transactional
    public Long commentSave(String nickname, Long id, CommentRequest dto) {
        User user = userRepository.findByNickname(nickname);
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + id));

        dto.setUser(user);
        dto.setPosts(posts);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();
    }

    /* UPDATE */
    @Transactional
    public void update(Long postsId, Long id, CommentDto.Request dto) {
        Comment comment = commentRepository.findByPostsIdAndId(postsId, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));

        comment.update(dto.getComment());
    }

    /* DELETE */
    @Transactional
    public void delete(Long postsId, Long id) {
        Comment comment = commentRepository.findByPostsIdAndId(postsId, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));

        commentRepository.delete(comment);
    }
}