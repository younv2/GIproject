package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.dto.request.LoginRequest;
import com.globalin.project.yorijori.dto.request.SignUpRequest;
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

    @GetMapping("/sign") // x
    public String signPage() {
        return "user/sign";
    }

    @PostMapping("/login") // 로그인 기능 구현
    public String login(@RequestBody LoginRequest request,
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
    public String register(@RequestBody SignUpRequest signUpRequest) { //다 적고 회원가입 버튼 눌렀어 그러면 여기로 와서 DB로 정보를 저장할수있는 로직처리
        userService.signUp(signUpRequest);
        return "redirect:/sign";
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

    @GetMapping("/checkid")
    public String checkId(@RequestParam String username)
    {
        userService.checkId(username);
        System.out.println(username + "111");
        return "/sign";
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
