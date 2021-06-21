package com.example.demo.src.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostRestaurantReq {
    private String restaurantName;
    private String restaurantLocation;
    private String restaurantNumber;
    private String variety;
    private int userId;
}
