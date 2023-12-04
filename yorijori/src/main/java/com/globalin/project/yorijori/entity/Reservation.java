package com.globalin.project.yorijori.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vno;

    @ManyToOne
    private User user;
    @ManyToOne
    private Restaurant restaurant;

    private LocalDateTime reservation_time;


}
