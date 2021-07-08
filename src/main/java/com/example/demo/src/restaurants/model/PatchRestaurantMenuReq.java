package com.example.demo.src.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchRestaurantMenuReq {
    private int userId;
    private int restaurantId;
    private String menuName;
    private int price;
}