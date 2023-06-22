package com.example.demo.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result<T> {
    private Integer code;//结果
    private String msg;//错误信息
    private T data;//返回数据
    private Map map = new HashMap();//动态数据


    public static <T> Result<T> success(T object,String msg){
        Result<T> r = new Result<T>();
        r.data=object;
        r.code=1;
        r.msg=msg;
        return r;
    }

    public static <T> Result<T> success(T object){
        Result<T> r = new Result<T>();
        r.data=object;
        r.code=1;
        return r;
    }
    public static <T> Result<T> error(String msg){
        Result<T> r = new Result<T>();
        r.msg=msg;
        r.code=0;
        return r;
    }
    public static <T> Result<T> error(String msg,Integer errorcode){
        Result<T> r = new Result<T>();
        r.msg=msg;
        r.code=errorcode;
        return r;
    }
    public Result<T> add(String key,Object value){
        this.map.put(key,value);
        return this;
    }



}
