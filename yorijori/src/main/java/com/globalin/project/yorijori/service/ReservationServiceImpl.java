package com.globalin.project.yorijori.service;

import com.globalin.project.yorijori.dto.response.ReservationResponse;
import com.globalin.project.yorijori.dto.response.RestaurantDetailResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.repository.ReservationRepository;
import com.globalin.project.yorijori.service.impl.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    @Override
    public ReservationResponse reservationRegister(UserResponse user, RestaurantDetailResponse detailResponse, LocalDateTime reservationTime) {
        return null;
    }

    @Override
    public void reservationDelete(UserResponse user, Long vno) {// 작업함

    }

    @Override
    public ReservationResponse reservationDetails(UserResponse user) {
        return null;
    }

    @Override
    public ReservationResponse reservationDetails(RestaurantDetailResponse res, UserResponse user) {
        return null;
    }
}
