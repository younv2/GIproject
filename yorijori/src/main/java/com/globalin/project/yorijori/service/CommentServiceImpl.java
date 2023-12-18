package com.globalin.project.yorijori.service;

import com.globalin.project.yorijori.dto.request.CommentRequest;
import com.globalin.project.yorijori.dto.response.CommentResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.entity.Comment;
import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.repository.CommentRepository;
import com.globalin.project.yorijori.repository.RestaurantRepository;
import com.globalin.project.yorijori.service.impl.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final RestaurantRepository rr;

    @Override
    public List<CommentResponse> commentDetails(Long rno)
    {
        List<Comment> comments =  commentRepository.findByRestaurant(rr.findById(rno).get());
        List<CommentResponse> commentsResponseList = new ArrayList<>();

        CommentResponse temp = new CommentResponse();
        for (Comment comment: comments) {
            System.out.println(comment.getUser().getNickname());
            temp.setNickname(comment.getUser().getNickname());
            temp.setRate(comment.getRate());
            temp.setReview(comment.getReview());
            commentsResponseList.add(temp);
        }

        return commentsResponseList;
    }

    @Override
    public void commentRegister(CommentRequest req) {
        Comment comment = new Comment();

        comment.setRate(req.getRate());
        comment.setReview(req.getReview());
        comment.setUser(req.getUser());
        comment.setRestaurant(req.getRestaurant());
        comment.setCreated_at(LocalDateTime.now());

        commentRepository.save(comment);

    }

    @Override
    public void commentDelete(UserResponse user, Long cno) {

    }
}
