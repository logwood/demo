package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Tables.EmployeeInfo;
import com.example.demo.Tables.Grades;
import com.example.demo.mapping.EmployeeInfoMapper;
import com.example.demo.mapping.GradesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeImpl extends ServiceImpl<GradesMapper, Grades> implements IGradesService {
    @Autowired
    private GradesMapper gradesMapper;

    public List<Grades> getgrade(String cname) {
        return gradesMapper.getgrade(cname);
    }
}