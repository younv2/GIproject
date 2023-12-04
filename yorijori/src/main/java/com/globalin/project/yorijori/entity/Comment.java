package com.globalin.project.yorijori.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;
    private Long mno;
    private Long rno;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

    private String review;
    private int rate;
    private LocalDateTime created_at;


}
