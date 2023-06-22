package com.example.demo.controller;

import com.example.demo.Tables.EmployeeInfo;
import com.example.demo.Tables.EmployeeInfoAll;
import com.example.demo.Tables.EmployeePrefer;
import com.example.demo.classofGreat.Stuff;
import com.example.demo.classofGreat.StuffOperator;
import com.example.demo.common.Result;
import com.example.demo.mapping.UserMapper;
import com.example.demo.service.EmployeeInfoServiceImpl;
import com.example.demo.service.EmployeePreferImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/sys/employeeInfos")
@Transactional
public class SendEmployeeController{
    @Autowired
    private EmployeeInfoServiceImpl employeeInfoService;
    @Autowired
    private EmployeePreferImpl employeePrefer;
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody EmployeeInfoAll employeeInfoAll){
        log.info("新增员工{}",employeeInfoAll.toString());
        employeeInfoService.save(employeeInfoAll);
        EmployeePrefer employeePrefer1=new EmployeePrefer();
        employeePrefer1.setPrefer(employeeInfoAll.getPrefer());
        employeePrefer1.setStaffId(employeeInfoAll.getStaffId());
        employeePrefer.save(employeePrefer1);
        return Result.success("员工信息成功录入");

    }
    @PostMapping("/update")
    public Result<String> update(@RequestBody EmployeeInfoAll employeeInfoAll){
        log.info("更新员工{}",employeeInfoAll.toString());
        employeeInfoService.updateById(employeeInfoAll);
        EmployeePrefer employeePrefer1=new EmployeePrefer();
        employeePrefer1.setPrefer(employeeInfoAll.getPrefer());
        employeePrefer1.setStaffId(employeeInfoAll.getStaffId());
        employeePrefer.updateById(employeePrefer1);
        return Result.success("员工信息成功更新");

    }
    @PostMapping("/delete")
    public Result<String> delete(@RequestBody EmployeeInfoAll employeeInfoAll){
        log.info("删除员工{}",employeeInfoAll.toString());
        EmployeePrefer employeePrefer1=new EmployeePrefer();
        employeePrefer1.setPrefer(employeeInfoAll.getPrefer());
        employeePrefer1.setStaffId(employeeInfoAll.getStaffId());
        employeePrefer.removeById(employeePrefer1);
        employeeInfoService.removeById(employeeInfoAll.getStaffId());
        return Result.success("员工信息成功删除");

    }
}
