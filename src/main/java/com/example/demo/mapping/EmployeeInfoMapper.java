package com.example.demo.mapping;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Tables.EmployeeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.Map;

@Mapper
public interface EmployeeInfoMapper extends BaseMapper<EmployeeInfo> {
        @Select("SELECT employee_info.*, employee_prefer.* FROM employee_info LEFT JOIN employee_prefer ON employee_info.id = employee_prefer.staff_id WHERE employee_info.shop_id = #{id}")
        LinkedHashMap<String, Object> selectWithB(Integer id);
}
