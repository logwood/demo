package com.example.demo.Tables;

import lombok.Data;

import java.io.Serializable;

@Data
public class userDto extends user_data implements Serializable {
    TestUser[] testUserList;
}
