package com.globalin.project.yorijori.dto.request;

import com.globalin.project.yorijori.entity.Role;
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
    private Role role;

}
