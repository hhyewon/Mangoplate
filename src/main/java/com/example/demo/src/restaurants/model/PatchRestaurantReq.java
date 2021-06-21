package com.example.demo.src.restaurants.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name = " RestaurantLike")
public class PatchRestaurantReq {

    private int userId;
    private int restaurantId;
    private String status;


}
