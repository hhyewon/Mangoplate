package com.example.demo.src.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "Restaurant")
public class GetRestaurantsRes {

//    @Column(name="id")
    private int id;
    private String restaurantUrl;
    private String restaurantName;
    private String restaurantLocation;
    private float distance;
    private int totalViewCount;
    private int totalReviewCount;
    private float rating;
    private int totalLike;
    private int isLike;
//
//    private float userLatitude;
//    private float userLongitude;
}
