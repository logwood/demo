package com.example.demo.mapping;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Tables.User;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
/**
 *
 * @author Wangsang
 * @date 2023/4/11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT employee_info.*, employee_prefer.* FROM employee_info JOIN employee_prefer ON employee_info.id = employee_prefer.staff_id WHERE employee_info.shop_id = #{id}")
    List<LinkedHashMap<String, Object>> selectWithB(Integer id);
}
