package com.example.demo.src.users.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name="User")
public class GetRestaurantVisitedRes {
    private int id;
    private String userUrl;
    private int totalReviewCount;
    private int followCount;
    private String nickname;
    private String restaurantName;
    private String restaurantLocation;
    private String reviewUrl;
    private String username;
    private int reviewCount;
    private int IsLike;
    private int totalReply;
    private String reply;
    private int isVisited;
    private String createdAt;
}
