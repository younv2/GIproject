package com.globalin.project.yorijori.dto.request;

import com.globalin.project.yorijori.entity.Category;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자

public class RestaurantRegistrationRequest {
    private Long rno;
    private String name;
    private String business_number;
    private String address;
    private String phone_number;
    private Category category;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String thumbnail;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private String duplicate_reservation;
    private Long user_mno;
}
