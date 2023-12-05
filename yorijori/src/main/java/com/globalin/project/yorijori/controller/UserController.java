package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.dto.request.LoginRequest;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/sign")
    public String signPage() {
        return "user/sign";
    }

    @PostMapping("/login")
    public String login(LoginRequest request,
                        HttpSession session) {
        UserResponse user = userService.login(request);
        session.setAttribute("username", "123");//임시로 "123"으로 변경 추후 작업 후 user.getUsername()로 바꿔주세요
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register() {
        return "redirect:";
    }

    @GetMapping("/myPage")
    public String userInfo(HttpSession session, Model model) {
        return "user/userInfo";
    }

    @GetMapping("/modify")
    public String modifyPage() {
        return "user/modifyPage";
    }

    @PutMapping("/modify")
    public String modify() {
        return "redirect:";
    }

    @GetMapping("/checkPassword")
    public String goToCheckPassword(HttpSession session,
                                Model model) {
        return "user/checkPassword";
    }

    @DeleteMapping("/deleted")
    public String checkPassword() {
        return "redirect:";
    }


}
