package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.service.impl.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    //등록
    public String register(HttpServletRequest request, HttpSession session)
    {

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
    //삭제
    public String delete(HttpServletRequest request, HttpSession session)
    {

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
