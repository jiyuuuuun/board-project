package com.example.boardproject.repository;

import com.example.boardproject.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByBoardId(Long boardId);
}
