package com.example.demo.src.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchRestaurantConvenienceRes {
    private int id;
    private String openHour;
    private String closeHour;
    private String breakTimeStart;
    private String breakTimeEnd;
    private String offDays;
    private String variety;
    private String isParking;
    private String userId;



}