package com.example.demo.mapping;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Tables.EmployeeInfo;
import com.example.demo.Tables.Grades;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;

@Mapper
public interface GradesMapper extends BaseMapper<Grades> {
    List<Grades> selectTable(String cname);
    List<Grades> getgrade(String cname);
}
