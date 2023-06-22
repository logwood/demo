package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.Tables.User;
import com.example.demo.common.Result;
import com.example.demo.service.EmployeeInfoServiceImpl;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/sys/register")
public class RegisterController extends HttpServlet {

    @Autowired
    private UserServiceImpl employeeInfoService;
    @PostMapping("/")
    public Result<String> login(HttpServletRequest request, @RequestBody User user){
        log.info("新增员工{}",user.toString());
        employeeInfoService.save(user);
        return Result.success("员工信息成功录入");
    }
    //退出,清理session
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request){

        request.getSession().removeAttribute("user");
        return Result.success(null,"退出成功");
    }

    @PostMapping("/save")
    public Result<String> save(@RequestBody User user){
        employeeInfoService.save(user);
        return Result.success("新增成功");
    }
}
