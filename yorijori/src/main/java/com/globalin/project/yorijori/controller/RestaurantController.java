package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.dto.request.RestaurantRegistrationRequest;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.service.impl.RestaurantService;
import com.globalin.project.yorijori.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final UserService userService;
    @GetMapping("/list")
    public String restaurantListPage() {
        return "restaurant/list";
    }

    @GetMapping("/details")
    public String details() {
        return "restaurant/details";
    }
    
    // 수정
    @GetMapping("/register")
    public String registerPage() {
        return "restaurant/register";
    }

    // 등록
    @PostMapping("/save")
        public String restaurantSave(HttpSession session, @ModelAttribute RestaurantRegistrationRequest restaurantRegistrationRequest){
        System.out.println("restaurantRegistrationRequest =" + restaurantRegistrationRequest);
        System.out.println("user =" + session.getAttribute("username"));
        UserResponse ur = userService.info((String)session.getAttribute("username"));
        restaurantService.restaurantRegistration(ur,restaurantRegistrationRequest);
        return "restaurant/list";
    }

    @GetMapping("/modify")
    public String modifyPage() {
        return "restaurant/modify";
    }
    
    //삭제
}
