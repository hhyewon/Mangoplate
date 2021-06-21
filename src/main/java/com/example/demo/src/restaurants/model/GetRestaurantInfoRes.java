package com.example.demo.src.restaurants.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "Restaurant")
public class GetRestaurantInfoRes {

//    @Column(name="id")
    private int id;
    private String updatedAt;
    private String businessHours;
    private String breakTime;
    private String offDays;
    private String variety;
    private String isParking;
    private String writer;
}
