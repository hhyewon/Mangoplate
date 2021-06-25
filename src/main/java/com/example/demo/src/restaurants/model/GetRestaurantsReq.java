package com.example.demo.src.restaurants.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "UserLocation")
public class GetRestaurantsReq {

    private float userLatitude;
    private float userLongitude;
}
