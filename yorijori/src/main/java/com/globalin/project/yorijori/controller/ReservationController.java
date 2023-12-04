package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.service.impl.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/")
    public String reservationPage() {
        return "user/reservation";
    }
    
    // 예약 성공

    @GetMapping("/details")
    public String reservationDetailsPage() {
        return "user/reservationDetails";
    }
    
    // 삭제
}
