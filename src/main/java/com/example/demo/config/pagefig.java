package com.example.demo.config;

import lombok.Data;

import java.io.Serializable;
@Data
public class pagefig implements Serializable {
    private int page;
    private int pagesize;
    private String name;

}
