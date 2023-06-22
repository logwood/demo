package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Tables.EmployeeInfoAll;
import com.example.demo.Tables.EmployeePrefer;
import com.example.demo.Tables.Work_table;
import com.example.demo.common.Result;
import com.example.demo.service.EmployeePreferImpl;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.service.WorkTableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/sys/workTable")
public class WorkTableController {
    @Autowired
    private WorkTableService workTableService;
    @PostMapping("/insert")
    public Result<String> insert(@RequestBody Work_table workTable){
        QueryWrapper<Work_table> queryWrapper=new QueryWrapper<>();
        boolean isExist = workTableService.lambdaQuery()
                .eq(Work_table::getStaffId, workTable.getStaffId())
                .count() > 0;
        if(isExist){// 统计符合条件的记录数
            return Result.error("工作表信息已经存在");
        }
        else {
            workTableService.save(workTable);
        }
        return Result.success("工作表信息成功插入");
    }
    @PostMapping("/update")
    public Result<String> update(@RequestBody Work_table workTable){
        workTableService.save(workTable);
        return Result.success("员工信息成功更新");

    }
    @PostMapping("/delete/{StaffId}")
    public Result<String> delete(@PathVariable Integer StaffID){
        boolean isNULL = workTableService.lambdaQuery()
                .eq(Work_table::getStaffId,StaffID)
                .count() == 0;
        if(isNULL)
            return Result.error("不存在该条记录",400);
        return Result.success("员工信息成功删除");

    }
    @PostMapping("/select/{StaffId}")
    public Result<String> select(@PathVariable Integer StaffID){
        QueryWrapper<Work_table> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("staff_id",StaffID);
        Work_table workTable= workTableService.getById(StaffID);
        return Result.success(workTable.toString(),"工作边信息查询成功");
    }
}
