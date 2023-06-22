package com.example.demo.mapping;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Tables.Course;
import com.example.demo.Tables.EmployeePrefer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseInfoMapper extends BaseMapper<Course> {
}
