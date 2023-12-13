package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.dto.request.LoginRequest;
import com.globalin.project.yorijori.dto.request.SignUpRequest;
import com.globalin.project.yorijori.dto.request.UserModifyRequest;
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
        String username = userService.login(request);
        if(username!=null)
        {
            session.setAttribute("username", username);
            System.out.println("로그인 완료");
            return "redirect:/";
        }
        else {
            System.out.println("로그인 실패");
            return null;
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(@RequestBody SignUpRequest signUpRequest) { //다 적고 회원가입 버튼 눌렀어 그러면 여기로 와서 DB로 정보를 저장할수있는 로직처리
        userService.signUp(signUpRequest);
        return "redirect:/user/sign";
    }

    @GetMapping("/myPage")
    public String userInfo(HttpSession session, Model model) {
        if(session.getAttribute("username") == null)
            return "user/sign";
        return "user/userInfo";
    }

    @GetMapping("/modify") //페이지에 들어왔을 때
    public String modifyPage(HttpSession session) {
        if(session.getAttribute("username") == null)
        return "user/sign";

        return "user/modify";
    }

    @PutMapping("/modify") // 수정버튼 누르고 작동하는 거
    public String modify(HttpSession session, @RequestBody UserModifyRequest userModifyRequest) {

            userService.userModify(userModifyRequest);
            return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/checkid")
    public boolean checkId(@RequestParam String username)
    {
        System.out.println(username);

        return userService.checkId(username);
    }
    @GetMapping("/checkPassword")
    public String goToCheckPassword(HttpSession session,
                                Model model) {
        if(session.getAttribute("username") == null)
            return "user/sign";

        return "user/checkPassword";
    }

    @ResponseBody
    @DeleteMapping("/deleted")
    public boolean userDelete(@RequestBody LoginRequest loginRequest,HttpSession session){
        boolean flag = userService.userDelete(loginRequest);
        if(flag)
        {
            System.out.println("통과");
            session.invalidate();
            return true;
        }
        else
            return false;
    }
}
