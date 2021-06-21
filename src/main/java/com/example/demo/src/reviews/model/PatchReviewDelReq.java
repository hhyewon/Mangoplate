package com.example.demo.src.reviews.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "Review")
public class PatchReviewDelReq {




    private String status;
    private int id;


}
