package com.example.demo.src.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "Restaurant")
public class GetRestaurantRes {

//    @Column(name="id")
    private int id;
    private String restaurantName;
    private String firstUrl;
    private String restaurantLocation;
    private int totalViews;
    private int totalReviews;
    private float rating;
    private int isLike;
    private int isVisited;


}
