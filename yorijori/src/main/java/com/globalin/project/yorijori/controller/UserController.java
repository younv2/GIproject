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

    @GetMapping("/login")
    public String loginPage() {
        return "user/sign";
    }

    @PostMapping("/login")
    public String login(LoginRequest request,
                        HttpSession session) {
        UserResponse user = userService.login(request);
        session.setAttribute("username", user.getUsername());
        return "redirect:";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        return "redirect:";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "restaurant/register";
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
