package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.entity.User;
import com.globalin.project.yorijori.service.impl.ReservationService;
import com.globalin.project.yorijori.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")  //reservation 매핑이 따로 필요한 이유가 뭘까
public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;

    @GetMapping("/")
    public String reservationPage(HttpSession session) {
        if(session.getAttribute("username") == null)
            return "user/sign";
        else
            return "user/reservation";
    }
    
    // 예약 성공
    @GetMapping("/reservation")
    public String reservationPage() {
        return "user/reservation";
    }

    @PostMapping("/resister")
    public String reservationResister(HttpSession session)
    {
        String username = (String)session.getAttribute("username");

        User user = userService.findByUsername(username);

        // reservationService.reservationRegister(user,);
        return "redirect:/";
    }

    
    // 삭제
}
