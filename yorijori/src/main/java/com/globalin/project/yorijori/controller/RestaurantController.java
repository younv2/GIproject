package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.dto.request.RestaurantRegistrationRequest;
import com.globalin.project.yorijori.dto.response.CommentResponse;
import com.globalin.project.yorijori.dto.response.RestaurantDetailResponse;
import com.globalin.project.yorijori.dto.response.RestaurantListResponse;
import com.globalin.project.yorijori.entity.Category;
import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.entity.User;
import com.globalin.project.yorijori.repository.RestaurantRepository;
import com.globalin.project.yorijori.repository.UserRepository;
import com.globalin.project.yorijori.service.impl.CommentService;
import com.globalin.project.yorijori.service.impl.RestaurantService;
import com.globalin.project.yorijori.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("/test/list/category")
    public String restaurantListPage(Model model, @RequestParam Category category) {
        System.out.println(category);
        List<RestaurantListResponse> restaurantListResponse = restaurantService.findByCategory(category);
        model.addAttribute("restaurantList", restaurantListResponse);
        return "restaurant/list";
    }

    @GetMapping("/test/list/name")
    public String restaurantListPage(Model model, @RequestParam String name) {
        System.out.println(name);
        List<RestaurantListResponse> RestaurantListResponse = restaurantService.findByName(name);
        model.addAttribute("restaurantList", RestaurantListResponse);
        return "restaurant/list";
    }

    @GetMapping("/list") // 페이징 처리 해야함
    public String listPage(Model model,
                           @RequestParam(required = false) String keyword,
                           @RequestParam(required = false) Category category
                           /*@RequestParam(required = false ) int page*/) {

        if(keyword == null && category == null) throw new RuntimeException("검색어와 카테고리를 선택해주세요");

        if (keyword != null) {
            List<RestaurantListResponse> RestaurantListResponse = restaurantService.findByName(keyword);
            model.addAttribute("restaurantList", RestaurantListResponse);
        } else {
            List<RestaurantListResponse> restaurantListResponse = restaurantService.findByCategory(category);
            model.addAttribute("restaurantList", restaurantListResponse);
        }

        return "restaurant/list";
    }

    @GetMapping("details/{rno}")
    public String details(@PathVariable("rno") Long rno, Model model) {
        System.out.println(rno);
        RestaurantDetailResponse restaurantDetailResponse = restaurantService.restaurantDetail(rno);
        List<CommentResponse> commentList = commentService.commentDetails(restaurantService.findById(rno));
        model.addAttribute("detail", restaurantDetailResponse);
        model.addAttribute("commentList", commentList);
        return "restaurant/details";
    }

    // 수정
    @GetMapping("/register")
    public String registerPage(HttpSession session) {
        if (session.getAttribute("username") == null)
            throw new RuntimeException("로그인 정보가 없습니다");
        return "restaurant/register";
    }

    // 등록
    @PostMapping(value = "/save", consumes = "multipart/form-data")
    public String restaurantSave(HttpSession session, @RequestPart(required = false) MultipartFile thumbnail, RestaurantRegistrationRequest restaurantRegistrationRequest) throws IOException {
        System.out.println("1");
        String userName = (String) session.getAttribute("username");

        User user = userService.findByUsername(userName);
        restaurantService.restaurantRegistration(user, restaurantRegistrationRequest, thumbnail);
        return "redirect:/";
    }

    @GetMapping("/modify/{rno}")
    public String modifyPage(@PathVariable Long rno, Model model, HttpSession session) {
        if (session.getAttribute("username") == null)
            throw new RuntimeException("로그인 정보가 없습니다");
        RestaurantDetailResponse restaurantDetailResponse = restaurantService.restaurantDetail(rno);
        model.addAttribute("restaurantModify", restaurantDetailResponse);
        return "restaurant/modify";
    }

    @PutMapping("/update/{rno}")
    public String updatePage(@PathVariable("rno") Long rno, @RequestBody RestaurantRegistrationRequest restaurantRegistrationRequest) {
        System.out.println("rno:" + rno);
        restaurantService.restaurantUpdate(rno, restaurantRegistrationRequest);
        return "redirect:/restaurant/details/" + rno;
    }

    //삭제
    @DeleteMapping("/delete/{rno}")
    @ResponseBody
    public Boolean deletePage(@PathVariable("rno") Long rno) {
        //Long userId = (Long) session.getAttribute("1");
        restaurantService.deleteRestaurant(rno);
        return true;
    }

    /*
    @GetMapping("/list/category")
    public String restaurantListPageTest(Model model, @RequestParam Category category, @RequestParam int page) {
        System.out.println(category);
        List<RestaurantListResponse> restaurantList = restaurantService.findByCategoryWithPaging(category,page);
        model.addAttribute("restaurantList", restaurantList);
        return "restaurant/list";
    }
     */
}
