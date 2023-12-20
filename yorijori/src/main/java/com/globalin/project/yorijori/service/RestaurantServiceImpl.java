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
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Override
    public void restaurantRegistration(User user, RestaurantRegistrationRequest req, MultipartFile thumbnail) throws IOException {
        //파일 첨부 여부에 따라 로직 분리
        //첨부 파일 없음.
        Restaurant restaurantEntity = Restaurant.toSaveEntity(req);
        if (!thumbnail.isEmpty()) {

                String uploadDir = "C:/thumbnail";
                //String uploadDir = "src/main/resources/static/thumbnail";

                String originalFileName = thumbnail.getOriginalFilename();

                String filePath = uploadDir + "/" + originalFileName;
                //String filePathForPrj = prjUploadDir +  "/" + originalFileName;

                restaurantEntity.setThumbnail(filePath);

                //파일 저장
                File dest = new File(filePath);

                thumbnail.transferTo(dest);
                //thumbnail.transferTo(dest);

        }else {
            throw new FileUploadException("업로드할 파일이 없습니다.");
        }

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


