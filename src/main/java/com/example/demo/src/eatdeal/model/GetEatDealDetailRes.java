package com.example.demo.src.eatdeal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "EatDeal")
public class GetEatDealDetailRes {

    private String eatDealUrl;
    private int eatDealPrice;
    private int eatDealDiscount;
    private int discountPrice;
    private String restaurantName;
    private String restaurantLocation;
    private String eatDealInfo;
    private String isPickUp;
    private String menuInfo;
    private String howToUse;
    private String refundPolicy;


}
