package com.globalin.project.yorijori.service;

import com.globalin.project.yorijori.dto.response.ReservationResponse;
import com.globalin.project.yorijori.dto.response.RestaurantDetailResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.entity.Reservation;
import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.entity.User;
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
    public void reservationRegister(User user, Restaurant restaurant, LocalDateTime reservationTime) {
        // 로그인한 정보 + 가게 정보 불러와서 DB에 저장하는 작업
        // 저장할 공간을 먼저 할당
        Reservation reservation = new Reservation();

        reservation.setReservation_time(reservationTime);
        reservation.setUser(user);
        reservation.setRestaurant(restaurant);

        reservationRepository.save(reservation);
    }

    @Override
    public void reservationDelete(UserResponse user, Long vno) {

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
