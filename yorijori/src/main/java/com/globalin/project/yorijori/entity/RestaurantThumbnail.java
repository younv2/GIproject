package com.globalin.project.yorijori.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Table(name = "restaurant_file_name")
public class RestaurantThumbnail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_rno")
    private Restaurant restaurant;

    public static RestaurantThumbnail toRestaurantThumbnail(Restaurant restaurant, String originalFileName, String storedFileName) {
        RestaurantThumbnail restaurantThumbnail = new RestaurantThumbnail();
        restaurantThumbnail.setOriginalFileName(originalFileName);
        restaurantThumbnail.setStoredFileName(storedFileName);
        restaurantThumbnail.setRestaurant(restaurant);
        return restaurantThumbnail;
    }
}
