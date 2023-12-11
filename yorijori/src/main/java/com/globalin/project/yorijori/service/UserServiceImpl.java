package com.globalin.project.yorijori.service;

import com.globalin.project.yorijori.dto.request.LoginRequest;
import com.globalin.project.yorijori.dto.request.SignUpRequest;
import com.globalin.project.yorijori.dto.request.UserModifyRequest;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.entity.User;
import com.globalin.project.yorijori.repository.UserRepository;
import com.globalin.project.yorijori.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
        userResponse.setMno(user.getMno());
        return userResponse;
    }

    @Override // 로그인기능 구현
    public UserResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setName(signUpRequest.getName());
        user.setNickname(signUpRequest.getNickname());
        user.setPhone_number(signUpRequest.getPhone_number());
        user.setPassword(signUpRequest.getPassword());
        user.setCreated_at(LocalDateTime.now());

        userRepository.save(user);
    }
    @Override
    public boolean checkId(String id)
    {
        User user = userRepository.findByUsername(id);
        if(user!=null)
            return false;
        else
            return true;
    }
    @Override
    public void userModify(UserModifyRequest userModifyRequest) {

    }

    @Override
    public void userDelete(LoginRequest loginRequest) {

    }
}
