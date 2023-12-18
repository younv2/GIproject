package com.globalin.project.yorijori.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentResponse {
    private String nickname;

    private String review;

    private int rate;
}
