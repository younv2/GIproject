package com.globalin.project.yorijori.service.impl;

import com.globalin.project.yorijori.dto.request.LoginRequest;
import com.globalin.project.yorijori.dto.request.SignUpRequest;
import com.globalin.project.yorijori.dto.request.UserModifyRequest;
import com.globalin.project.yorijori.dto.response.LoginResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.entity.Role;
import com.globalin.project.yorijori.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService {

    LoginResponse login(LoginRequest loginRequest);

    UserResponse info(String username);

    void signUp(SignUpRequest signUpRequest);

    boolean checkId(String id);

    void userModify(UserModifyRequest userModifyRequest);

    void userModify(Role role, String id);

    boolean userDelete(LoginRequest loginRequest);

    User findByUsername(String username);
}
