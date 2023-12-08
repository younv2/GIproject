package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.dto.request.RestaurantRegistrationRequest;
import com.globalin.project.yorijori.entity.User;
import com.globalin.project.yorijori.repository.UserRepository;
import com.globalin.project.yorijori.service.impl.RestaurantService;
import com.globalin.project.yorijori.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final UserRepository userRepository;
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
        public String restaurantSave(HttpSession session, @RequestBody RestaurantRegistrationRequest restaurantRegistrationRequest){
        //System.out.println("restaurantRegistrationRequest =" + restaurantRegistrationRequest);
        session.setAttribute("username","123");
        String userName = (String)session.getAttribute("username");
        //System.out.println("user =" + userName);

        User user = userRepository.findByUsername(userName);
        restaurantService.restaurantRegistration(user,restaurantRegistrationRequest);
        return "redirect:/restaurant/list";
    }

    @GetMapping("/modify")
    public String modifyPage() {
        return "restaurant/modify";
    }
    
    //삭제
}
