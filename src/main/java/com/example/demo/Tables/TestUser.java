package com.example.demo.Tables;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestUser implements Serializable {
        public String id;
        public String name;
        public String password;
}
