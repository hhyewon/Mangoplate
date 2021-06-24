package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchUserRes {
    private int id;
    private String nickname;
    private String email;
    private String PhoneNumber;
    private String userUrl;
}