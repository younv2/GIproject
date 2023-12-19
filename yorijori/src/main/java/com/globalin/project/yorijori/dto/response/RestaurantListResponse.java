package com.globalin.project.yorijori.dto.response;

import com.globalin.project.yorijori.entity.Category;
import com.globalin.project.yorijori.entity.Restaurant;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자

public class RestaurantListResponse {
    private Long rno;
    private String name;
    private String category;
    private String thumbnail;
    private String description;

    public RestaurantListResponse(Long rno, String name, String category, String thumbnail, String description) {
        this.rno = rno;
        this.name = name;
        this.category = category;
        this.thumbnail = thumbnail;
        this.description = description;
    }

    public RestaurantListResponse(Long rno, String name, Category category, String thumbnail, String description) {

    }

    public static RestaurantListResponse toRestaurantListResponse(Restaurant restaurantEntity) {
        RestaurantListResponse restaurantListResponse = new RestaurantListResponse();
        restaurantListResponse.setRno(restaurantEntity.getRno());
        restaurantListResponse.setName(restaurantEntity.getName());
        restaurantListResponse.setCategory(restaurantEntity.getCategory().name());
        restaurantListResponse.setThumbnail(restaurantEntity.getThumbnail());
        restaurantListResponse.setDescription(restaurantEntity.getDescription());

        return restaurantListResponse;
    }
}
