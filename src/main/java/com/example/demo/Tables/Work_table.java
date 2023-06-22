package com.example.demo.Tables;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.example.demo.methods.ComplexTypeHandler;
import lombok.Data;
import org.apache.ibatis.type.ArrayTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@TableName(value = "work_table",autoResultMap = true)
@Data
public class Work_table{
    private static final long serialVersionUID = 1L;


    @TableId(value = "staff_id")
    private Integer staffId;
    private String stuffName;
    //mybatis-plus指定typehandler
    @TableField(typeHandler = ComplexTypeHandler.class)
    private work_time[] workTimes;
    public work_time[] getWorkTimes() {
        return workTimes;
    }
}
