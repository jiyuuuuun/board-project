package com.example.boardproject.controller;

import com.example.boardproject.entity.Board;
import com.example.boardproject.entity.Comment;
import com.example.boardproject.service.BoardService;
import com.example.boardproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    //글 등록 폼
    @GetMapping("/writeform")
    public String form(Model model) {
        model.addAttribute("board",new Board());
        return "/writeform";
    }

    //글 저장
    @PostMapping("/save")
    public String save(@ModelAttribute Board board) {
        boardService.save(board);
        return "redirect:/board/list";
    }
    //글 목록
    @GetMapping("/list")
    public String list(@RequestParam(name="page",required = false,defaultValue ="1") int page,
                       @RequestParam(name="size",required = false,defaultValue ="6") int size, Model model) {
        Pageable pageable = PageRequest.of(page-1,size);
        model.addAttribute("boards",boardService.findAll(pageable));
        return "list";
    }

    //게시글 상세 조회
    @GetMapping("/view")
    public String view(@RequestParam(name="id",required = false,defaultValue = "0") Long id, Model model) {
        Board board = boardService.findOne(id);
        model.addAttribute("board",board);
        Comment comment=new Comment(id);
        System.out.println(comment);
        model.addAttribute("comment",comment);
        List<Comment> commentList=commentService.getCommentByBoardId(id);
        model.addAttribute("commentList",commentList);
        return "view";
    }
    //글 수정
    @GetMapping("/updateform")
    public String update(@RequestParam(name="id",required = false,defaultValue = "0") Long id,
                         @ModelAttribute("msg") String msg, Model model) {
        System.out.println(boardService.findOne(id));
        model.addAttribute("board",boardService.findOne(id));

        return "updateform";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Board board,@RequestParam(name="inputPassword") String inputPassword,RedirectAttributes redirectAttributes) {
        if (boardService.checkpassword(board,inputPassword)){
            boardService.update(board);
            return "redirect:/board/list";
        }else{
            //return "redirect:/board/updateform?msg=비밀번호가 틀립니다"; 헤더를 통해 보낼 때 인코딩 오류!
            //세션을 사용하여 데이터를 전달하므로 URL에 직접 포함되지 않아 인코딩 문제 해결
            redirectAttributes.addFlashAttribute("msg", "비밀번호가 틀립니다");
            return "redirect:/board/updateform?id=" + board.getId();

        }

    }

    //글 삭제
    @GetMapping("/deleteform")
    public String delete(@RequestParam(name="id",required = false,defaultValue = "0") Long id,
                         @ModelAttribute("msg") String msg,Model model){
        model.addAttribute("board",boardService.findOne(id));
        return "/deleteform";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Board board,
                         @RequestParam(name="inputPassword") String inputPassword,RedirectAttributes redirectAttributes){
        if (boardService.checkpassword(board,inputPassword)){
            boardService.delete((long) board.getId());
            return "redirect:/board/list";
        }else{
            redirectAttributes.addFlashAttribute("msg", "비밀번호가 틀립니다");
            return "redirect:/board/deleteform?id=" + board.getId();

        }

    }



}
