package com.globalin.project.yorijori.dto.request;

import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequest {
    private Long rno;

    private Long mno;

    private String review;

    private int rate;

    private User user;

    private Restaurant restaurant;
}
