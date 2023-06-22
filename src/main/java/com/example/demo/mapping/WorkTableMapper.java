package com.example.demo.mapping;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Tables.Work_table;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkTableMapper extends BaseMapper<Work_table> {
    void insertTest();
}
