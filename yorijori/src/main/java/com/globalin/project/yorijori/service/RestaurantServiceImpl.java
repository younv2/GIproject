package com.globalin.project.yorijori.service;

import com.globalin.project.yorijori.dto.request.RestaurantRegistrationRequest;
import com.globalin.project.yorijori.dto.response.RestaurantDetailResponse;
import com.globalin.project.yorijori.dto.response.RestaurantListResponse;
import com.globalin.project.yorijori.dto.response.UserResponse;
import com.globalin.project.yorijori.entity.Category;
import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.entity.User;
import com.globalin.project.yorijori.repository.RestaurantRepository;
import com.globalin.project.yorijori.service.impl.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Restaurant> optionalRestaurantEntity = restaurantRepository.findById(rno);
        if(optionalRestaurantEntity.isPresent()) {
            Restaurant restaurantEntity = optionalRestaurantEntity.get();
            RestaurantDetailResponse restaurantDetailResponse = RestaurantDetailResponse.toRestaurantDetailResponse(restaurantEntity);
            return restaurantDetailResponse;
        }else{
            return null;
        }

    }

    @Override
    public List<Restaurant> showRestaurantList(Category category) {
        return null;
    }

    @Override
    public List<Restaurant> showRestaurantList(String name) {
        return null;
    }


    @Override
    public void restaurantModify(Long rno, RestaurantRegistrationRequest req) {
        Restaurant restaurant = Restaurant.toUpdateEntity(req);
        restaurantRepository.save(restaurant);
    }



    @Override
    public RestaurantRegistrationRequest restaurantUpdate(Long rno, RestaurantRegistrationRequest req) {
        Restaurant restaurant = Restaurant.toUpdateEntity(req);
        restaurantRepository.save(restaurant);
        return findByRno(req.getRno());
    }


    @Override
    public void restaurantDelete(UserResponse user, Long rno) {

    }

    @Override
    public List<RestaurantListResponse> findAll() {
        return null;
    }

    @Override
    public List<RestaurantListResponse> findByCategory(Category category) {
        List<Restaurant> restaurantEntityList = restaurantRepository.findByCategoryOrderByRnoDesc(category);
        List<RestaurantListResponse> RestaurantDTO = new ArrayList<>();

        for (Restaurant restaurantEntity : restaurantEntityList) {
            RestaurantDTO.add(RestaurantListResponse.toRestaurantListResponse(restaurantEntity));
        }
        return RestaurantDTO;
    }

    @Override
    public List<RestaurantListResponse> findByName(String restaurant) {
        List<Restaurant> restaurantEntityList = restaurantRepository.findByNameContainingOrderByRnoDesc(restaurant);
        List<RestaurantListResponse> RestaurantDTO = new ArrayList<>();

        for (Restaurant restaurantEntity : restaurantEntityList) {
            RestaurantDTO.add(RestaurantListResponse.toRestaurantListResponse(restaurantEntity));
        }
        return RestaurantDTO;
    }

    @Override
    public RestaurantRegistrationRequest findByRno(Long rno) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(rno);
        if(optionalRestaurant.isPresent()) {
            Restaurant restaurantEntity = optionalRestaurant.get();
            RestaurantRegistrationRequest req = RestaurantRegistrationRequest.toRestaurantDTO(restaurantEntity);
            return req;
        }else{
            return null;
        }
    }
}


