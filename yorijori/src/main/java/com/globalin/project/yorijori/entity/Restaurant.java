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
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String name;
    private String buisness_number;
    private String address;
    private String phone_number;

    @Enumerated(EnumType.STRING)
    private Category category;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String thumbnail;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private String duplicate_reservation;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Reservation> reservations;
}
