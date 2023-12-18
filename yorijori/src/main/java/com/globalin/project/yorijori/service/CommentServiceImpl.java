package com.globalin.project.yorijori.service;

import com.globalin.project.yorijori.dto.request.CommentRequest;
import com.globalin.project.yorijori.dto.response.CommentResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.repository.CommentRepository;
import com.globalin.project.yorijori.service.impl.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public List<CommentResponse> commentDetails(Long rno) {
        return null;
    }

    @Override
    public void commentRegister(CommentRequest req) {

    }

    @Override
    public void commentDelete(UserResponse user, Long cno) {

    }
}
