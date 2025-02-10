package com.example.boardproject.service;

import com.example.boardproject.entity.Board;
import com.example.boardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    //글 등록 이름, 제목, 암호, 본문을 입력
    @Transactional
    public void save(Board board) {
        if (board.getCreatedAt() == null) {
            board.setCreatedAt(LocalDateTime.now());
        }
        if (board.getUpdatedAt() == null) {
            board.setUpdatedAt(LocalDateTime.now());
        }
        boardRepository.save(board);
    }

    //글 목록 보기 최신 글부터 보여짐, 페이징 처리, ID, 제목, 이름, 등록일(YYYY/MM/DD) 형식
    @Transactional
    public Page<Board> findAll(Pageable pageable) {
        Pageable p= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

        return boardRepository.findAll(p);
    }

    //글 상세 조회 암호는 보여지면 안됨
    @Transactional
    public Board findOne(Long id) {
        return boardRepository.findById(Math.toIntExact(id)).get();
    }

    //글 수정
    @Transactional
    public void update(Board board) {
        boardRepository.save(board);
    }

    //글 삭제
    @Transactional
    public void delete(Long id) {
            Board board=boardRepository.findById(Math.toIntExact(id)).get();
            boardRepository.delete(board);

    }
    public boolean checkpassword(Board board, String password) {
        if (board.getPassword().equals(password)) {
            return true;
        }else{
            return false;
        }
    }



}
