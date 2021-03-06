package com.example.demo.src.reviews.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "Review")
public class Reviews {
   private int id;
    private int reviewId;
    private int userId;
    private float score;
    private int restaurantId;
//    private String userName;
    private String comment;
   private String reviewUrl;

    private String status;
//    private int userId;
//    @DateTimeFormat(pattern = "yyyy-MM")
//    @JsonFormat( pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
//    private LocalDate createdAt;
}
