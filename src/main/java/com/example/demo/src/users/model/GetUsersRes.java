package com.example.demo.src.users.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name="User")
public class GetUsersRes {
    private int id;
    private String email;
    private String password;
    private String phoneNumber;
    private String userUrl;
    private String nickname;
}
