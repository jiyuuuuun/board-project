package com.example.boardproject.controller;

import com.example.boardproject.entity.Board;
import com.example.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

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
        model.addAttribute("board",boardService.findOne(id));
        return "view";
    }
    //글 수정
    @GetMapping("/updateform")
    public String update(@RequestParam(name="id",required = false,defaultValue = "0") Long id,Model model) {
        model.addAttribute("board",boardService.findOne(id));
        return "updateform";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Board board,@RequestParam(name="password") String password){
        if (boardService.checkpassword(board,password)){
            boardService.update(board);
            return "redirect:/board/list";
        }else{
            return "redirect:/board/updateform";
        }

    }

    //글 삭제
    @GetMapping("/deleteform")
    public String delete(@RequestParam(name="id",required = false,defaultValue = "0") Long id,Model model){
        model.addAttribute("board",boardService.findOne(id));
        return "/deleteform";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Board board,
                         @RequestParam(name="password") String password){
        if (boardService.checkpassword(board,password)){
            boardService.delete((long) board.getId());
            return "redirect:/board/list";
        }else{
            return "redirect:/board/deleteform";
        }

    }



}
