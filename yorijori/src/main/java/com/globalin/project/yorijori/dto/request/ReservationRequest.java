package com.globalin.project.yorijori.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {
     private LocalDateTime reservation_time;
}
