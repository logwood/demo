package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Tables.Course;
import com.example.demo.Tables.EmployeeInfo;
import com.example.demo.mapping.CourseInfoMapper;
import com.example.demo.mapping.EmployeeInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class CourseImpl  extends ServiceImpl<CourseInfoMapper, Course> implements ICourseInfoService {
}
