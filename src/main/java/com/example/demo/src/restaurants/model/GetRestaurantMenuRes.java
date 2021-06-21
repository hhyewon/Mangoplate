package com.example.demo.src.restaurants.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "RestaurantMenu")
public class GetRestaurantMenuRes {

    private int restaurantId;
    private String updatedAt;
    private String menuName;
    private int price;


}
