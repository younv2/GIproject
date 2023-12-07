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

    @Override
    public UserResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {

    }

    @Override
    public void userModify(UserModifyRequest userModifyRequest) {

    }

    @Override
    public void userDelete(LoginRequest loginRequest) {

    }
}
