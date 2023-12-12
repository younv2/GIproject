package com.globalin.project.yorijori.dto.response;

import com.globalin.project.yorijori.entity.Restaurant;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자

public class RestaurantListResponse {
    private Long rno;
    private String name;
    private String category;
    private String thumbnail;
    private String description;

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
