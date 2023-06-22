package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.example.demo.Tables.User;
import com.example.demo.common.Result;
import com.example.demo.service.UserServiceImpl;
import jakarta.servlet.http.Cookie;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

import static com.example.demo.Tables.methods.bytesToString;

@RestController
@RequestMapping("/sys/user")
public class LoginQuerys extends HttpServlet {
    @Autowired
    private UserServiceImpl userService;
    //@Autowired
    //private RedisTemplate redisTemplate;
    @PostMapping("/login")
    public Result<User> login(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws IOException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getStaffId,user.getStaffId());
        User u = userService.getOne(queryWrapper);
        //ValueOperations<String,User> operations=redisTemplate.opsForValue();
        String key = "user_";
        String password = user.getUsercode();

        if (u == null){
            return Result.error("用户名不存在，请先注册");
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(bos);
        oos.writeObject(user);
        byte[] bytes = bos.toByteArray();
        //operations.set(key,user,5, TimeUnit.HOURS);
        //2.登录成功,保存用户登录标记
        Cookie cookie=new Cookie("user", bytesToString(bytes));
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        response.addCookie(cookie);
        if (!u.getUsercode().equals(password)){
            return Result.error("密码不正确，请重新登陆");
        }
        //管理员登录
        if (u.getUseriden()==1){
            return  Result.success(u,"管理员用户登录成功");
        }
        //将id保存到session
        request.getSession().setAttribute("user",u.getStaffId());
        return Result.success(u,"员工登录成功");

    }
    //退出,清理session
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");

        return Result.success(null,"退出成功");
    }

    @PostMapping("/save")
    public Result<String> save(@RequestBody User user){
        userService.save(user);
        return Result.success("新增成功");
    }
}