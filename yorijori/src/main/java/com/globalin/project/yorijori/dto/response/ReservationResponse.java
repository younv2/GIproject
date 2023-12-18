package com.globalin.project.yorijori.dto.response;

import com.globalin.project.yorijori.entity.Restaurant;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ReservationResponse {

    private Restaurant restaurant;
    private LocalDateTime reservation_time;

}
