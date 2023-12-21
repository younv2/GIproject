package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.entity.User;
import com.globalin.project.yorijori.service.impl.ReservationService;
import com.globalin.project.yorijori.service.impl.RestaurantService;
import com.globalin.project.yorijori.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{rno}")
    public String reservationPage(HttpSession session, @PathVariable Long rno, Model model) {
        if (session.getAttribute("username") == null)
            return "user/sign";
        else {
            model.addAttribute("rno", rno);
            return "user/reservation";
        }
    }


    @PostMapping("/register/{rno}")
    public String reservationResister(HttpSession session, @PathVariable Long rno, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reservation_time) {
        String username = (String) session.getAttribute("username");
        Restaurant restaurant = restaurantService.findById(rno);
        User user = userService.findByUsername(username);


        reservationService.reservationRegister(user, restaurant, reservation_time);
        return "redirect:/";

    }


    // 삭제

    @DeleteMapping("/delete/{vno}")
    public String reservationDelete(HttpSession session, @PathVariable("vno") Long vno) {
        String username = (String) session.getAttribute("username");
        reservationService.reservationDelete(userService.findByUsername(username), vno);
        return "redirect:/";

    }
}
