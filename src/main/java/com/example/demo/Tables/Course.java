package com.example.demo.Tables;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("course")
public class Course implements Serializable {
    String CID;
    String CNAME;
    String Teacher;
}
