package com.example.demo.src.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name="RestaurantVisited")
public class PostRestaurantVisitedRes {
    private int id;

}
