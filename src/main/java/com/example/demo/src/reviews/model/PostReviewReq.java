package com.example.demo.src.reviews.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PostReviewReq {
//    private int id;
    private int userId;
    private int restaurantId;
    private String comment;


}
