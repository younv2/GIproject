package com.globalin.project.yorijori.service;

import com.globalin.project.yorijori.dto.request.LoginRequest;
import com.globalin.project.yorijori.dto.request.SignUpRequest;
import com.globalin.project.yorijori.dto.request.UserModifyRequest;
import com.globalin.project.yorijori.dto.response.LoginResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.entity.Reservation;
import com.globalin.project.yorijori.entity.Role;
import com.globalin.project.yorijori.entity.User;
import com.globalin.project.yorijori.repository.UserRepository;
import com.globalin.project.yorijori.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse info(String username) {
        User user = userRepository.findByUsername(username);
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(username);
        userResponse.setNickname(user.getNickname());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone_number(user.getPhone_number());
        return userResponse;
    }

    @Override // 로그인기능 구현
    public LoginResponse login(LoginRequest loginRequest) {
        // loginrequest.Username을 DB에서 찾아서 일치하는지 여부를 진행 맞으면 로그인 성공하는 절차

        String id = loginRequest.getUsername();
        User user = userRepository.findByUsername(id);

        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setRole(user.getRole());
        loginResponse.setUsername(user.getUsername());

        if (user == null) {
            System.out.println("유저가 존재하지 않음");
            return null;
        } else {
            if (loginRequest.getPassword().equals(user.getPassword())) {
                System.out.println("비밀번호 정상");
                return loginResponse;
            } else {
                System.out.println("비밀번호가 틀림" + loginRequest.getPassword() + "   " + user.getPassword());
                return null;
            }
        }
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setName(signUpRequest.getName());
        user.setNickname(signUpRequest.getNickname());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(Role.MEMBER);
        user.setPhone_number(signUpRequest.getPhone_number());
        user.setPassword(signUpRequest.getPassword());
        user.setCreated_at(LocalDateTime.now());

        userRepository.save(user);
    }

    @Override
    public boolean checkId(String id) {
        User user = userRepository.findByUsername(id);
        if (user != null)
            return false;
        else
            return true;
    }

    @Override
    public void userModify(UserModifyRequest userModifyRequest) {

        String id = userModifyRequest.getUsername(); // ModifyRequest값으로 받은 Username을 id로 저장
        User user = userRepository.findByUsername(id); // id로 찾은 정보를 user객체로 저장

        //user객체의 정보를 업데이트하는 작업

        user.setName(userModifyRequest.getName());
        user.setPassword(userModifyRequest.getPassword());
        user.setNickname(userModifyRequest.getNickname());
        user.setPhone_number(userModifyRequest.getPhone_number());
        user.setEmail(userModifyRequest.getEmail());
        user.setRole(userModifyRequest.getRole());

        userRepository.save(user);

    }
    @Override
    public void userModify(Role role,String id) {
        User user = userRepository.findByUsername(id); // id로 찾은 정보를 user객체로 저장

        //user객체의 정보를 업데이트하는 작업

        user.setRole(role);

        userRepository.save(user);

    }

/*    @Override
    public void userDelete(LoginRequest loginRequest) {

        User user = userRepository.findByUsername(loginRequest.getUsername());

        if (loginRequest.getPassword().equals(user.getPassword())) {
            userRepository.delete(user);
        }*/

    @Override
    public boolean userDelete(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());

        if (loginRequest.getPassword().equals(user.getPassword())) {
            userRepository.delete(user);
            return true;
        } else
            return false;
    }





    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}
