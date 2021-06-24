package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Users {
    private int id;
    private String nickname;
    private String email;
    private String password;
//    private String PhoneNumber;
//    private String userUrl;
}
