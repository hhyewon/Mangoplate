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
    private String restaurantName;
    private String firstUrl;
    private String restaurantLocation;
    private float distance;
    private int totalViews;
    private int totalReviews;
    private float rating;
    private int isLike;
}
