package com.example.boardproject.controller;

import com.example.boardproject.entity.Comment;
import com.example.boardproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/write")
    public String write(@ModelAttribute Comment comment, Model model) {
        System.out.println(comment);
        commentService.saveComment(comment);
        return "redirect:/board/view?id=" + comment.getBoardId(); //기본적으로 뷰 템플릿을 반환하므로 redirect 를 붙여줘야함 !
    }

}
