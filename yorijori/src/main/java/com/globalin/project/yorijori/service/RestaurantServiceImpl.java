package com.globalin.project.yorijori.service;

import com.globalin.project.yorijori.dto.request.RestaurantRegistrationRequest;
import com.globalin.project.yorijori.dto.response.RestaurantDetailResponse;
import com.globalin.project.yorijori.dto.response.RestaurantListResponse;
import com.globalin.project.yorijori.entity.Category;
import com.globalin.project.yorijori.entity.Restaurant;
import com.globalin.project.yorijori.entity.User;
import com.globalin.project.yorijori.repository.RestaurantRepository;
import com.globalin.project.yorijori.repository.UserRepository;
import com.globalin.project.yorijori.service.impl.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Override
    public void restaurantRegistration(User user, RestaurantRegistrationRequest req) throws IOException {

        //파일 첨부 여부에 따라 로직 분리
        if(req.getThumbnail().isEmpty()) {
            //첨부 파일 없음.
            Restaurant restaurantEntity = Restaurant.toSaveEntity(req);
            restaurantEntity.setUser(user);
            restaurantRepository.save(restaurantEntity);
        }else {
            //첨부 파일 있음.
            /*
            1. DTO에 담긴 파일을 꺼냄
            2. 파일의 이름 가져옴
            3.서버 저장용 이름을 만듦
            4. 저장 경로 설정
            5. 해당 경로에 파일 저장
            6. 레스토랑에 해당 데이터 save 처리
            7. 레스토랑 파일에 해당 데이터 save처리
             */

            MultipartFile thumbnailImage = req.getThumbnailFile();//1
            String originalFileName = thumbnailImage.getOriginalFilename(); //2
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName; // 3.
            String savePath = "C:/thumbnail/" + storedFileName;
            thumbnailImage.transferTo(new File(savePath));
        }


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
    public void restaurantUpdate(Long rno, RestaurantRegistrationRequest req) {

            Restaurant restaurantEntity = Restaurant.toUpdateEntity(req);
            restaurantEntity.setRno(rno);
            restaurantRepository.save(restaurantEntity);

    }

    @Override
    public Restaurant restaurantDelete(Long userId, Long rno) {
        return null;
    }

    @Override
    public void deleteRestaurant(Long rno) {
        restaurantRepository.deleteById(rno);
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
    public Restaurant findByRestaurantName(String name) {
        return restaurantRepository.findByName(name);
    }
    @Override
    public Restaurant findById(Long id) {

        return restaurantRepository.findById(id).get();
    }

    @Override
    public List<RestaurantListResponse> findByCategoryWithPaging(Category category, int page) {
        Pageable pageable = PageRequest.of(page,3);
        List<Restaurant> restaurants = restaurantRepository.findByCategory(category, pageable);
        List<RestaurantListResponse> restaurantListResponses = new ArrayList<>();
        for (Restaurant restaurant :restaurants)
        {
            restaurantListResponses.add(RestaurantListResponse.toRestaurantListResponse(restaurant));
        }
        return restaurantListResponses;

    }
}


