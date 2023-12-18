package com.globalin.project.yorijori.repository;

import  com.globalin.project.yorijori.entity.Category;
import com.globalin.project.yorijori.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    //가게 검색
    List<Restaurant> findByNameContainingOrderByRnoDesc(String name);
    List<Restaurant> findByCategoryOrderByRnoDesc(Category category);
    List<Restaurant> findByCategory(Category category, Pageable pageable);
    Restaurant findByName(String name);
}
