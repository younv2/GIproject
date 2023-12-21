package com.globalin.project.yorijori.controller;

import com.globalin.project.yorijori.dto.request.LoginRequest;
import com.globalin.project.yorijori.dto.request.SignUpRequest;
import com.globalin.project.yorijori.dto.request.UserModifyRequest;
import com.globalin.project.yorijori.dto.response.CommentResponse;
import com.globalin.project.yorijori.dto.response.ReservationResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.entity.Reservation;
import com.globalin.project.yorijori.entity.Role;
import com.globalin.project.yorijori.entity.User;
import com.globalin.project.yorijori.service.impl.ReservationService;
import com.globalin.project.yorijori.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
        String username = userService.login(request).getUsername();
        Role role = userService.login(request).getRole();
        if (username != null) {
            System.out.println(role);
            session.setAttribute("role", role.name());
            session.setAttribute("username", username);
//            session.setAttribute("user", userService.info(username));  세션 저장 방식 변경 검토
            System.out.println("로그인 완료");
            return "redirect:/";
        } else {
            System.out.println("로그인 실패");
            throw new RuntimeException("로그인 실패");
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
        if (session.getAttribute("username") == null)
            throw new RuntimeException("로그인 정보가 없습니다");

        User user = userService.findByUsername((String) session.getAttribute("username"));
        List<Reservation> reservationList = user.getReservations();
        List<ReservationResponse> reservationResponseList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            ReservationResponse temp = new ReservationResponse();
            temp.setName(reservation.getRestaurant().getName());
            temp.setReservation_time(reservation.getReservation_time());
            reservationResponseList.add(temp);
        }
        UserResponse userResponse = userService.info((String) session.getAttribute("username"));
        model.addAttribute("userInfo", userResponse);
        model.addAttribute("reservationList", reservationResponseList);
        return "user/userInfo";
    }

    @PutMapping("/manager")
    @ResponseBody
    public void setManager(HttpSession session) {
        userService.userModify(Role.MANAGER, (String) session.getAttribute("username"));
        session.setAttribute("role", Role.MANAGER.name());
    }

    @GetMapping("/modify") //페이지에 들어왔을 때
    public String modifyPage(HttpSession session, Model model) {
        if (session.getAttribute("username") == null)
            throw new RuntimeException("로그인 정보가 없습니다");
        UserResponse userResponse = userService.info((String) session.getAttribute("username"));
        model.addAttribute("userInfo", userResponse);
        return "user/modify";
    }

    @PutMapping("/modify") // 수정버튼 누르고 작동하는 거
    public String modify(@RequestBody UserModifyRequest userModifyRequest) {

        userService.userModify(userModifyRequest);
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/checkid")
    public boolean checkId(@RequestParam String username) {
        System.out.println(username);

        return userService.checkId(username);
    }

    @GetMapping("/checkPassword")
    public String goToCheckPassword(HttpSession session,
                                    Model model) {
        if (session.getAttribute("username") == null)
            throw new RuntimeException("로그인 정보가 없습니다");

        return "user/checkPassword";
    }


/*    @DeleteMapping("/delete")
    public String userDelete(LoginRequest loginRequest){

        userService.userDelete(loginRequest);

       return "redirect:/user/sign";
    }*/


    @ResponseBody
    @DeleteMapping("/delete")
    public boolean userDelete(@RequestBody LoginRequest loginRequest, HttpSession session) {
        boolean flag = userService.userDelete(loginRequest);
        if (flag) {
            System.out.println("통과");
            session.invalidate();
            return true;
        } else
            return false;
    }
}
