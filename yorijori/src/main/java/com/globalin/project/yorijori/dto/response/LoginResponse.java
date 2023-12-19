package com.globalin.project.yorijori.dto.response;

import com.globalin.project.yorijori.entity.Role;
import lombok.Data;

@Data
public class LoginResponse {
    private String username;

    private Role role;
}
