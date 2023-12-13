package com.globalin.project.yorijori.dto.response;

import com.globalin.project.yorijori.entity.Restaurant;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ReservationResponse {

    // 예약시 DB에서 불러올 회원 정보
    private Long mno;


    // 예약시 DB에서 불러올 가게 정보
    private Long rno;


    // 예약시 DB에서 불러올 예약정보
    private Long vno;
    private Restaurant restaurant;
    private LocalDateTime reservation_time;

}
