package com.globalin.project.yorijori.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;
    private String name;
    private String password;
    private String email;
    private String phone_number;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String username;
    private String nickname;
    private LocalDateTime created_at;


    @OneToMany
    private List<Comment> comments;

    @OneToMany
    private List<Reservation> reservations;

    @OneToOne
    private Restaurant restaurant;
}
