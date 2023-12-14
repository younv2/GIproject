package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.entity.User;
import com.globalin.project.yorijori.service.impl.ReservationService;
import com.globalin.project.yorijori.service.impl.RestaurantService;
import com.globalin.project.yorijori.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;
    private final RestaurantService restaurantService;

    @GetMapping("/")
    public String reservationPage(HttpSession session) {
        if(session.getAttribute("username") == null)
            return "user/sign";
        else
            return "user/reservation";
    }


    @PostMapping("/resister")
    public String reservationResister(HttpSession session, @RequestParam LocalDateTime reservation_time) {
        String username = (String)session.getAttribute("username");
        String restaurantName = (String)session.getAttribute("restaurantName");

        User user = userService.findByUsername(username);
        Restaurant restaurant = restaurantService.findByRestaurantName(restaurantName);

        reservationService.reservationRegister(user, restaurant, reservation_time);
        return "redirect:/";

    }

    
    // 삭제
}
