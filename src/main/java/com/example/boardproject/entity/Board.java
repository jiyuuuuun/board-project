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
@Table("board")
public class Board {
    @Id
    private int id;
    private String name;
    private String title;
    private String password;
    private String content;
    @Column("created_at")
    private LocalDateTime createdAt= LocalDateTime.now();
    @Column("updated_at")
    private LocalDateTime updatedAt= LocalDateTime.now();

}
