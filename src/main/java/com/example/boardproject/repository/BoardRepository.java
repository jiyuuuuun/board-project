package com.example.boardproject.repository;

import com.example.boardproject.entity.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<Board,Integer>, PagingAndSortingRepository<Board,Integer> {
}
