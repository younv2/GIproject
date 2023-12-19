package com.globalin.project.yorijori.dto.request;

import com.globalin.project.yorijori.entity.Category;

import com.globalin.project.yorijori.entity.Restaurant;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자

public class RestaurantRegistrationRequest {
    private Long rno;
    private String name;
    private String business_number;
    private String address;
    private String phone_number;
    private Category category;
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime start_time;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime end_time;
    private String duplicate_reservation;

    public static RestaurantRegistrationRequest toRestaurantDTO(Restaurant restaurantEntity){
        RestaurantRegistrationRequest restaurantDTO = new RestaurantRegistrationRequest();
        restaurantDTO.setRno(restaurantEntity.getRno());
        restaurantDTO.setName(restaurantEntity.getName());
        restaurantDTO.setAddress(restaurantEntity.getAddress());
        restaurantDTO.setCategory(restaurantEntity.getCategory());
        restaurantDTO.setDescription(restaurantEntity.getDescription());
        restaurantDTO.setStart_time(restaurantEntity.getStart_time());
        restaurantDTO.setEnd_time(restaurantEntity.getEnd_time());
        restaurantDTO.setDuplicate_reservation(restaurantEntity.getDuplicate_reservation());

        return restaurantDTO;
    }
}
