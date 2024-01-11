package com.globalin.project.yorijori.service;

import com.globalin.project.yorijori.dto.request.CommentRequest;
import com.globalin.project.yorijori.dto.response.CommentResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.entity.Comment;
import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.repository.CommentRepository;
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

    @Override
    public List<CommentResponse> commentDetails(Restaurant restaurant) {
        List<Comment> comments =  commentRepository.findByRestaurant(restaurant);
        List<CommentResponse> commentsResponseList = new ArrayList<>();

        for (Comment comment: comments) {
            CommentResponse temp = new CommentResponse();
            temp.setNickname(comment.getUser().getNickname());
            temp.setRate(comment.getRate());
            temp.setReview(comment.getReview());
            temp.setCno(comment.getCno());
            commentsResponseList.add(temp);
        }
        return commentsResponseList;
    }

    @Override
    public void commentRegister(CommentRequest req) {
        Comment comment = new Comment();
        comment = commentRepository.findByUser(req.getUser());
        if(comment == null)
            comment.setCreated_at(LocalDateTime.now());
        comment.setRate(req.getRate());
        comment.setReview(req.getReview());
        comment.setUser(req.getUser());
        comment.setRestaurant(req.getRestaurant());


        commentRepository.save(comment);
    }

    @Override
    public void commentDelete(String user, Long cno)
    {
        Comment comment = commentRepository.findById(cno).get();

        if(!comment.getUser().getUsername().equals(user))
        {
            throw new RuntimeException("아이디가 맞지 않습니다");
        }
        else
        {
            commentRepository.delete(comment);
        }
    }
}
