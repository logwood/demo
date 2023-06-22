package com.example.demo.Tables;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("user_data")
public class user_data {
    @TableId(value = "id")
    String keynum;
    String id;
    Date keydate;
}
