package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.Tables.Shop;
import com.example.demo.Tables.User;
import com.example.demo.classofGreat.Store;
import com.example.demo.classofGreat.Stuff;
import com.example.demo.classofGreat.StuffOperator;
import com.example.demo.classofGreat.main;
import com.example.demo.common.Result;
import com.example.demo.mapping.EmployeeShopMapper;
import com.example.demo.mapping.UserMapper;
import com.example.demo.service.EmployeePreferImpl;
import com.example.demo.service.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

import static com.example.demo.Tables.methods.bytesToString;
@Slf4j
@RestController
@RequestMapping("/sys/scheduling")
public class SchedulingController extends HttpServlet {
    @Autowired
    private UserMapper userService;
    @Autowired
    private EmployeeShopMapper employeeShopMapper;
    @GetMapping("/query/id={newpwd}")
    public Result<Integer> login(HttpServletRequest request, HttpServletResponse response,@PathVariable("newpwd")String newpwd) throws Exception {
        int a=Integer.parseInt(newpwd);
        List<LinkedHashMap<String, Object>> ans= userService.selectWithB(a);
        if (ans == null){
            return Result.error("不存在该结果");
        }
        LinkedHashMap<String, Object> map=ans.get(0);
        map.keySet().removeIf(key ->key.equals("staff_id")||key.equals("prefer_type")||key.equals("id"));
        HashMap<String, Stuff> stuff_arr = StuffOperator.stuff_arrInit();
        for(Map<String, Object> maps:ans)
        {
            String prefer=(String)maps.get("prefer");
            int how=Integer.parseInt(String.valueOf(maps.get("shop_id")));
            stuff_arr.put(new String((String)maps.get("NAME") ),new Stuff((String)maps.get("NAME"),(String)maps.get("position"),(String)maps.get("telephone"),(String)maps.get("email"),String.valueOf(how),new ArrayList<>(Arrays.asList(prefer.split(" ")))));
          //可能会有问题
        }
        Shop shop=employeeShopMapper.selectById(Integer.parseInt(newpwd));
        shop.toString();
        Store store=new Store(shop.getName(),shop.getAddress(),shop.getSize(),stuff_arr);
        return Result.success(0,"成功得到需求");

    }

}

