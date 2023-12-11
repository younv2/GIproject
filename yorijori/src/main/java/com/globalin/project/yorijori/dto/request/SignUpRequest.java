package com.globalin.project.yorijori.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class SignUpRequest {
    private String nickname;
    private String name;
    private String username;
    private String email;
    private String phone_number;
    private String password;

}
