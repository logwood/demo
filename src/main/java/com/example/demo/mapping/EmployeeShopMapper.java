package com.example.demo.mapping;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Tables.Shop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeShopMapper extends BaseMapper<Shop> {

}