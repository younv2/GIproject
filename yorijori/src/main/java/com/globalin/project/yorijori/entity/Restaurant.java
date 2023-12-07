package com.globalin.project.yorijori.entity;

import com.globalin.project.yorijori.dto.request.RestaurantRegistrationRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private String duplicate_reservation;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    public static Restaurant toSaveEntity(RestaurantRegistrationRequest restaurantRegistrationRequest){
        Restaurant restaurantEntity = new Restaurant();
        restaurantEntity.setRno(restaurantRegistrationRequest.getRno());
        restaurantEntity.setName(restaurantRegistrationRequest.getBusiness_number());
        restaurantEntity.setAddress(restaurantRegistrationRequest.getPhone_number());
        restaurantEntity.setCategory(restaurantRegistrationRequest.getCategory());
        restaurantEntity.setDescription(restaurantRegistrationRequest.getThumbnail());
        restaurantEntity.setStart_time(restaurantRegistrationRequest.getStart_time());
        restaurantEntity.setEnd_time(restaurantRegistrationRequest.getEnd_time());
        return restaurantEntity;
    }

}
