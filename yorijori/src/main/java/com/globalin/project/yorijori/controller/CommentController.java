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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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
            throw new RuntimeException("로그인 정보가 없습니다");

        commentRequest.setUser(userService.findByUsername((String) session.getAttribute("username")));
        commentRequest.setRestaurant(restaurantService.findById(commentRequest.getRno()));

        commentService.commentRegister(commentRequest);

        return "redirect:" + referer;
    }

    //삭제
    @PostMapping("/delete")
    public String delete(HttpServletResponse response, HttpServletRequest request, HttpSession session, Long cno) throws IOException {

        try {
            commentService.commentDelete((String) session.getAttribute("username"), cno);
        } catch (RuntimeException e)
        {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디가 맞지 않습니다');history.go(-1);</script>");
            out.flush();
            response.flushBuffer();
            out.close();
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
