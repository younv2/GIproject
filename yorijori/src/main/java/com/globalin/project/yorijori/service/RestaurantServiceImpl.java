package com.globalin.project.yorijori.service;

import com.globalin.project.yorijori.dto.request.RestaurantRegistrationRequest;
import com.globalin.project.yorijori.dto.response.RestaurantDetailResponse;
import com.globalin.project.yorijori.dto.response.RestaurantListResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.entity.Category;
import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.entity.User;
import com.globalin.project.yorijori.repository.RestaurantRepository;
import com.globalin.project.yorijori.repository.UserRepository;
import com.globalin.project.yorijori.service.impl.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;



    @Override
    public void restaurantRegistration(User user, RestaurantRegistrationRequest req) {
        Restaurant restaurantEntity = Restaurant.toSaveEntity(req);

        restaurantEntity.setUser(user);
        restaurantRepository.save(restaurantEntity);
    }

    @Override
    public RestaurantDetailResponse restaurantDetail(Long rno) {
        return null;
    }

    @Override
    public List<RestaurantListResponse> showRestaurantList(Category category) {
        return null;
    }

    @Override
    public List<RestaurantListResponse> showRestaurantList(String name) {
        return null;
    }

    @Override
    public void restaurantModify(Long rno, RestaurantRegistrationRequest req) {

    }

    @Override
    public void restaurantDelete(UserResponse user, Long rno) {

    }
}
