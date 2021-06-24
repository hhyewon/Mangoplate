package com.example.demo.src.users.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name="User")
public class GetUserRes {
    private int id;
    private String userUrl;
    private String nickname;
    private int totalFollowCount;
    private int totalReviewCount;
    private int totalVisited;
    private int totalLike;
}
