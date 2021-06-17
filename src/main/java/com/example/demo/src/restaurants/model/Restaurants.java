package com.example.demo.src.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Restaurants {

    @Getter
    @Setter
    @AllArgsConstructor
    public class User {
        private int id;
        private String restaurantName;
        private String restaurantImage ;
        private String location;
        private String views;
    }


}
