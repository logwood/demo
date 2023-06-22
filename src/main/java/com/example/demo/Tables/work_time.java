package com.example.demo.Tables;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.demo.methods.ComplexTypeHandler;
import lombok.Data;

import java.sql.Date;

@Data

public class work_time {
    Date date;
    String workTime;

}
