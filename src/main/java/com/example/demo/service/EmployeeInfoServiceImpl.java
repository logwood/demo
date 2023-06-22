package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Tables.EmployeeInfo;
import com.example.demo.Tables.User;
import com.example.demo.mapping.EmployeeInfoMapper;
import com.example.demo.mapping.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeInfoServiceImpl extends ServiceImpl<EmployeeInfoMapper, EmployeeInfo> implements IEmployeeInfoService {


}