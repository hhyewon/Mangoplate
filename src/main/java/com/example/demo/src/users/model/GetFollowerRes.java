package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name="Follow")
public class GetFollowerRes {
//    private int userId;

    private int followId;
    private String nickname;
    private int totalFollowCount;
    private int totalReviewCount;
}
