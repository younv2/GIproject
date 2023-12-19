package com.globalin.project.yorijori.dto.response;

import com.globalin.project.yorijori.entity.Restaurant;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자

public class RestaurantDetailResponse {

    private Long rno;
    private String name;
    private String category;
    private String address;
    private String thumbnail;
    private String description;
    private String phone_number;
    private LocalDateTime start_time;
    private LocalDateTime end_time;


    public static RestaurantDetailResponse toRestaurantDetailResponse(Restaurant restaurantEntity) {
        RestaurantDetailResponse restaurantDetailResponse = new RestaurantDetailResponse();
        restaurantDetailResponse.setRno(restaurantEntity.getRno());
        restaurantDetailResponse.setName(restaurantEntity.getName());
        restaurantDetailResponse.setAddress(restaurantEntity.getAddress());
        restaurantDetailResponse.setCategory(restaurantEntity.getCategory().name());
        restaurantDetailResponse.setPhone_number(restaurantEntity.getPhone_number());
        restaurantDetailResponse.setStart_time(restaurantEntity.getStart_time());
        restaurantDetailResponse.setEnd_time(restaurantEntity.getEnd_time());
        restaurantDetailResponse.setThumbnail(restaurantEntity.getThumbnail());
        restaurantDetailResponse.setDescription(restaurantEntity.getDescription());

        return restaurantDetailResponse;
    }
}
