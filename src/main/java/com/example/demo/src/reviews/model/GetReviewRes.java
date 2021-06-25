package com.example.demo.src.reviews.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "Review")
public class GetReviewRes {

//    @Column(name="id")
    private int id;
    private int restaurantId;
    private String userUrl;
    private String nickname;
    private int totalReviewCount;
    private int followCount;
    private float score;
    private String scoreIcon;
    private String restaurantName;
    private String restaurantLocation;
    private String comment;
    private String reviewUpdatedAt;
    private String reviewUrl;
    private String reply;
    private String replyUpdatedAt;
    private int replyTotal;

}
