package com.globalin.project.yorijori.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long mno;
    private String nickname;
    private String name;
    private String username;
    private String email;
    private String phone_number;
    private String role;
    private LocalDateTime created_at;
}
