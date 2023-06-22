package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Tables.EmployeeInfo;
import com.example.demo.common.Result;
import com.example.demo.config.pagefig;
import com.example.demo.service.EmployeeInfoServiceImpl;
import jakarta.validation.constraints.Null;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/sys/employees")
public class EmployeeController {
    @Autowired
    private EmployeeInfoServiceImpl employeeInfoService;

    @PostMapping
    public Result<String> save(@RequestBody EmployeeInfo employeeInfo){
        log.info("新增员工{}",employeeInfo.toString());
        employeeInfoService.save(employeeInfo);
        return Result.success("员工信息成功录入");

    }

    /**分页查询
     *
     * @param fig
     * @return
     */
    @GetMapping("/page")
    public Result<Page> select(pagefig fig){
        //分页大小
        Page pageInfo = new Page(fig.getPage(),fig.getPagesize());
        //条件构造器
        LambdaQueryWrapper<EmployeeInfo> queryWrapper = new LambdaQueryWrapper();
        //过滤条件
        String a=fig.getName();
        queryWrapper.like(StringUtils.hasLength(fig.getName()),EmployeeInfo::getName, fig.getName());


        //执行查询
        Page<EmployeeInfo> result=employeeInfoService.page(pageInfo,queryWrapper);
        pageInfo.setRecords(result.getRecords());
        pageInfo.setTotal(result.getTotal());
        return Result.success(pageInfo);
    }

    /**修改信息
     *
     * @param employeeInfo
     * @return
     */
    @PutMapping("/updateinfo")
    public Result<String> update(@RequestBody EmployeeInfo employeeInfo){
        employeeInfoService.updateById(employeeInfo);
        return Result.success("修改员工信息成功");

    }

    /**根据id查询员工信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<String> getById(@PathVariable("id") int id){
        EmployeeInfo employeeInfo = employeeInfoService.getById(id);
        if (id!= 0){
            return Result.success("查询id成功");
        }
        return Result.error("id不存在");

    }
}
