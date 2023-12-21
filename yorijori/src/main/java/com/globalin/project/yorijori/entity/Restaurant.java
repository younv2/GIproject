package com.globalin.project.yorijori.entity;

import com.globalin.project.yorijori.dto.request.RestaurantRegistrationRequest;
import com.globalin.project.yorijori.dto.response.RestaurantDetailResponse;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.LongBinaryOperator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String name;
    private String business_number;
    private String address;
    private String phone_number;

    @Enumerated(EnumType.STRING)
    private Category category;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String thumbnail;
    private String thumbnail_path;
    private LocalTime start_time;
    private LocalTime end_time;
    private int duplicate_reservation;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    public static Restaurant toSaveEntity(RestaurantRegistrationRequest restaurantRegistrationRequest){
        Restaurant restaurantEntity = new Restaurant();
        restaurantEntity.setName(restaurantRegistrationRequest.getName());
        restaurantEntity.setAddress(restaurantRegistrationRequest.getAddress());
        restaurantEntity.setCategory(restaurantRegistrationRequest.getCategory());
        restaurantEntity.setDescription(restaurantRegistrationRequest.getDescription());
        restaurantEntity.setStart_time(restaurantRegistrationRequest.getStart_time());
        restaurantEntity.setEnd_time(restaurantRegistrationRequest.getEnd_time());
        restaurantEntity.setBusiness_number(restaurantRegistrationRequest.getBusiness_number());
        restaurantEntity.setPhone_number(restaurantRegistrationRequest.getPhone_number());
        restaurantEntity.setDuplicate_reservation(restaurantRegistrationRequest.getDuplicate_reservation());

        return restaurantEntity;
    }


    public static Restaurant toUpdateEntity( RestaurantRegistrationRequest restaurantRegistrationRequest) {
        Restaurant restaurantEntity = new Restaurant();
        restaurantEntity.setRno(restaurantRegistrationRequest.getRno());
        restaurantEntity.setName(restaurantRegistrationRequest.getName());
        restaurantEntity.setAddress(restaurantRegistrationRequest.getAddress());
        restaurantEntity.setCategory(restaurantRegistrationRequest.getCategory());
        restaurantEntity.setDescription(restaurantRegistrationRequest.getDescription());
        restaurantEntity.setStart_time(restaurantRegistrationRequest.getStart_time());
        restaurantEntity.setEnd_time(restaurantRegistrationRequest.getEnd_time());
        restaurantEntity.setBusiness_number(restaurantRegistrationRequest.getBusiness_number());
        restaurantEntity.setPhone_number(restaurantRegistrationRequest.getPhone_number());
        restaurantEntity.setDuplicate_reservation(restaurantRegistrationRequest.getDuplicate_reservation());

        return restaurantEntity;

    }
}
