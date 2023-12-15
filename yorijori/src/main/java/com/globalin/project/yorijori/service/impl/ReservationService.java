package com.globalin.project.yorijori.service.impl;

import com.globalin.project.yorijori.dto.response.ReservationResponse;
import com.globalin.project.yorijori.dto.response.RestaurantDetailResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.entity.User;

import java.time.LocalDateTime;

public interface ReservationService {
    
    //예약 등록
    void reservationRegister(User user,
                             Restaurant restaurant,
                             LocalDateTime reservationTime);

    //예약 삭제
    void reservationDelete(User user, Long vno);

    //예약 조회
    ReservationResponse reservationDetails(UserResponse user);

    ReservationResponse reservationDetails(RestaurantDetailResponse res,
                                           UserResponse user);

}
