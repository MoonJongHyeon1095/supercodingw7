package com.github.crudprac.repository;

import com.github.crudprac.entity.CommentsEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentsJdbcDao implements CommentsRepository {

    private JdbcTemplate jdbcTemplate;

    static RowMapper<CommentsEntity> commentsEntityRowMapper = (((rs, rowNum) ->
            new CommentsEntity(
                    rs.getInt("id"),
                    rs.getInt("like_count"),
                    rs.getNString("content"),
                    rs.getDate("created_at")

            )));

    public CommentsJdbcDao(@Qualifier("jdbcTemplate1") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CommentsEntity> findAllComments() {
        return jdbcTemplate.query("SELECT * FROM comments", commentsEntityRowMapper);
    }

    @Override
    public Integer saveComments(CommentsEntity commentsEntity) {
        jdbcTemplate.update("INSERT INTO item(likeCount, content, createdDate) VALUES (?, ?, ?)",
                commentsEntity.getLikeCount(), commentsEntity.getContent(), commentsEntity.getCreatedDate()
        );

        CommentsEntity commentsEntityFound = jdbcTemplate.queryForObject("SELECT * FROM comments WHERE name = ?", commentsEntityRowMapper, commentsEntity.getId());
        return commentsEntityFound.getId();
    }

    @Override
    public CommentsEntity updateCommentsEntity(Integer idInt, CommentsEntity commentsEntity) {
        jdbcTemplate.update("UPDATE comments " +
                        "SET likeCount = ?, content = ?, createdDate = ?" +
                        "WHERE id = ?",
                commentsEntity.getLikeCount(), commentsEntity.getContent(), commentsEntity.getCreatedDate(),
                idInt);

        return jdbcTemplate.queryForObject("SELECT * FROM comments WHERE id = ?", commentsEntityRowMapper, idInt);
    }

    @Override
    public void deleteComments(int idInt) {
        jdbcTemplate.update("DELETE FROM comments WHERE id = ?", idInt);
    }

    @Override
    public CommentsEntity findCommentsById(Integer idInt) {
        return jdbcTemplate.queryForObject("SELECT * FROM comments WHERE id = ?", commentsEntityRowMapper, idInt);
    }

    @Override
    public void updateCommentsStock(Integer itemId, Integer i) {

    }

    @Override
    public void updateCommentsStock(Integer commentsId, String content) {
        jdbcTemplate.update("UPDATE comments " +
                " SET content = ? " +
                " WHERE id = ? ", content, commentsId);
    }
}