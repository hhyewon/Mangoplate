package com.example.demo.src.users.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name="Restaurant")
public class GetRestaurantLikeRes {

    private int userId;
    private String reviewUrl;
    private String restaurantLocation;
    private String restaurantName;
    private int views;
    private int reviews;
    private int isLike;

}
