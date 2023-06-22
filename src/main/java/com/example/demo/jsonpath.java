package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.Tables.employees;

import java.util.*;

public class jsonpath {
    public static void parseJson(String[] args) {

        WeakHashMap<String,Object> map = new WeakHashMap<>();
        WeakHashMap<String,Object> map2 = new WeakHashMap<>();
        WeakHashMap<String,Object> map21 = new WeakHashMap<>();
        WeakHashMap<String,Object> map211 = new WeakHashMap<>();
        WeakHashMap<String,Object> map212 = new WeakHashMap<>();
        List<String> Date= List.of(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday","Sunday"});
        for(String dat:Date)
        {
            List<employees> empList = new ArrayList<employees>();
            for(int i=0;i<=12;i++) {
                ArrayList<String> obj = new ArrayList<String>();
                List list = Arrays.asList("1", "2", "3", "4", "5", "6");
                obj.addAll(list);
                employees employee = new employees(obj);
                empList.add(employee);
            }
            List<employees> list = new ArrayList<employees>(empList);
            map.put(dat,list);
        }
        List<WeakHashMap>maps=new ArrayList<>(List.of(new WeakHashMap[]{map}));
        map2.put("List",maps);

        String jsonString = JSON.toJSONString(map2);
        System.out.println(jsonString);
    }


}