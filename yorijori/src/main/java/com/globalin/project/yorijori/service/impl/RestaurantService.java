package com.globalin.project.yorijori.service.impl;

import com.globalin.project.yorijori.dto.request.RestaurantRegistrationRequest;
import com.globalin.project.yorijori.dto.response.RestaurantDetailResponse;
import com.globalin.project.yorijori.dto.response.RestaurantListResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.entity.Category;
import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.entity.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface RestaurantService {
    //가게 등록
    void restaurantRegistration(User user, RestaurantRegistrationRequest req);

    //가게 상세페이지
    RestaurantDetailResponse restaurantDetail(Long rno);

    //카테고리별 조회
    List<Restaurant> showRestaurantList(Category category);

    //레스토랑 이름 검색
    List<Restaurant> showRestaurantList(String name);

    //가게 정보 수정
    void restaurantModify(Long rno, RestaurantRegistrationRequest req);

    //가게 삭제
    void restaurantDelete(UserResponse user, Long rno);

    List<RestaurantListResponse> findAll();

    List<RestaurantListResponse> findByCategory(Category category);

    List<RestaurantListResponse> findByName(String restaurant);
}
