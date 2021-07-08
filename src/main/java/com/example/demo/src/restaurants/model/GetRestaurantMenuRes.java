package com.example.demo.src.restaurants.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Table;
import javax.xml.transform.Result;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "RestaurantMenu")
public class GetRestaurantMenuRes {
//    @SerializedName("a")
//    private int restaurantId;
    private Example example;
    private String menuName;
    private int price;

    public GetRestaurantMenuRes(int restaurantId, String updatedAt, String menuName, int price) {
//        this.restaurantId = restaurantId;
        this.example = new Example();
//        this.updatedAt = updatedAt;
        this.menuName = menuName;
        this.price = price;
    }
}
