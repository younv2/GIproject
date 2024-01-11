package com.globalin.project.yorijori.repository;

import com.globalin.project.yorijori.entity.Comment;
import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByRestaurant(Restaurant rno);

    Comment findByUser(User user);
}
