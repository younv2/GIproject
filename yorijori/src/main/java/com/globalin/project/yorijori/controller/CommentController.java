package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.dto.request.CommentRequest;
import com.globalin.project.yorijori.service.impl.CommentService;
import com.globalin.project.yorijori.service.impl.RestaurantService;
import com.globalin.project.yorijori.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final RestaurantService restaurantService;

    //등록
    @PostMapping("/register")
    public String register(HttpServletRequest request, HttpSession session, CommentRequest commentRequest) {
        String referer = request.getHeader("Referer");
        if (session.getAttribute("username") == null)
            return "redirect:" + referer;

        commentRequest.setUser(userService.findByUsername((String) session.getAttribute("username")));
        commentRequest.setRestaurant(restaurantService.findById(commentRequest.getRno()));
        
        commentService.commentRegister(commentRequest);

        return "redirect:" + referer;
    }

    //삭제
    @PostMapping("/delete")
    public String delete(HttpServletRequest request, HttpSession session, Long cno) {
        session.getAttribute("username");
        System.out.println(cno);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
