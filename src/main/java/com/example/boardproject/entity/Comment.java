package com.example.boardproject.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("comment")
public class Comment {


    //댓글 ID (PK)
    @Id
    private Long id;

    //Spring Data JDBC는 JPA처럼 @ManyToOne을 사용하지 않고, 외래 키(FK)는 명시적으로 컬럼으로 저장
    //게시글 삭제 시 관련 댓글도 삭제(CASCADE)
    //게시글 ID (FK)
    @Column("board_id")
    private Long boardId;

    private String author;

    private String content;

    private String password;

    @Column("created_at")
    private LocalDateTime createTime= LocalDateTime.now();

    public Comment(Long boardId) {
        this.boardId = boardId; //꼭 객체에 넣기!!!!!
    }


}
