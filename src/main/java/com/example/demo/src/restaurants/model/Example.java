package com.example.demo.src.restaurants.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@Table(name = "RestaurantMenu")
public class Example {
    private int restaurantId;
    private String updatedAt;
//    String name = "colt";
}
