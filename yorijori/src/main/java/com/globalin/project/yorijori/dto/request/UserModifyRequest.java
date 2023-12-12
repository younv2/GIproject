package com.globalin.project.yorijori.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserModifyRequest {

    private String password;
    private String nickname;
    private String name;
    private String username;
    private String email;
    private String phone_number;
    private String role;

}
