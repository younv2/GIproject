package com.globalin.project.yorijori.service.impl;

import com.globalin.project.yorijori.dto.request.CommentRequest;
import com.globalin.project.yorijori.dto.response.CommentResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.entity.Restaurant;

import java.util.List;

public interface CommentService {
    //댓글 및 평점 조회?
    List<CommentResponse> commentDetails(Long rno);
    //댓글 및 평점 등록
    void commentRegister(CommentRequest req);
    //댓글 및 평점 삭제
    void commentDelete(UserResponse user, Long cno);
}
