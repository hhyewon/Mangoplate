package com.example.demo.src.reviews.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "Review")
public class PatchReviewReq {

    private int id;
    private int userId;
    private float score;
    private String reviewUrl;
    private String comment;


}
