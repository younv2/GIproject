package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.service.impl.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    private final ReservationService reservationService;

    @GetMapping("/list")
    public String restaurantListPage() {
        return "restaurant/list";
    }

    @GetMapping()
    public String details() {
        return "restaurant/details";
    }
    
    // 수정

    @GetMapping()
    public String registerPage() {
        return "restaurant/register";
    }
    
    // 등록

    @GetMapping()
    public String modifyPage() {
        return "restaurant/modify";
    }
    
    //삭제
}
