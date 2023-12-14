package com.globalin.project.yorijori.repository;

import  com.globalin.project.yorijori.entity.Category;
import com.globalin.project.yorijori.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    //가게 검색
    List<Restaurant> findByNameContainingOrderByRnoDesc(String name);
    List<Restaurant> findByCategoryOrderByRnoDesc(Category category);
    Restaurant findByName(String name);
}
