package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.dto.request.RestaurantRegistrationRequest;
import com.globalin.project.yorijori.dto.response.RestaurantDetailResponse;
import com.globalin.project.yorijori.dto.response.RestaurantListResponse;
import com.globalin.project.yorijori.entity.Category;
import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.entity.User;
import com.globalin.project.yorijori.repository.RestaurantRepository;
import com.globalin.project.yorijori.repository.UserRepository;
import com.globalin.project.yorijori.service.impl.RestaurantService;
import com.globalin.project.yorijori.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;


    @GetMapping("/list/category")
    public String restaurantListPage(Model model, @RequestParam Category category) {
        List<RestaurantListResponse> restaurantListResponse = restaurantService.findByCategory(category);
        model.addAttribute("restaurantList", restaurantListResponse);
        return "restaurant/list";
    }

    @GetMapping("/list/name/{name}")
    public String restaurantListPage(Model model, @PathVariable("name") String name){
        System.out.println(name);
        List<RestaurantListResponse> RestaurantListResponse = restaurantService.findByName(name);
        model.addAttribute("restaurantList", RestaurantListResponse);
        return "restaurant/list";
    }

    @GetMapping("/details/{rno}")
    public String details(@PathVariable("rno")Long rno, Model model) {
        System.out.println(rno);
        RestaurantDetailResponse restaurantDetailResponse = restaurantService.restaurantDetail(rno);
        model.addAttribute("detail",restaurantDetailResponse);
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
        return "redirect:/";
    }

    @GetMapping("/modify")
    public String modifyPage() {
        return "restaurant/modify";
    }

    
    //삭제
}
