package com.example.boardproject.service;

import com.example.boardproject.entity.Comment;
import com.example.boardproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Comment saveComment(Comment comment) {
        comment.setId(null);

        return commentRepository.save(comment);
    }
    @Transactional(readOnly = true)
    public List<Comment> getCommentByBoardId(Long boardId) {
        return commentRepository.findByBoardId(boardId);
    }
}
