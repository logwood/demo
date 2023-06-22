package com.example.demo.methods;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.demo.Tables.Work_table;
import com.example.demo.Tables.work_time;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@Service
public class ComplexTypeHandler extends BaseTypeHandler<work_time[]> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public ComplexTypeHandler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, work_time[] parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setNull(i, Types.OTHER);
          return;
        }
        final Array array = jdbcTemplate.getDataSource().getConnection().createArrayOf("complex_type",Arrays.stream(parameter)
                .map(w -> String.format("(%s,'%s')", w.getDate(), w.getWorkTime()))
                .toArray(String[]::new));


        ps.setArray(i, array);
        System.out.println(ps);
    }

    @Override
    public work_time[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getArray(rs.getArray(columnName));
    }

    @Override
    public work_time[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getArray(rs.getArray(columnIndex));
    }

    @Override
    public work_time[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getArray(cs.getArray(columnIndex));
    }

    private work_time[] getArray(Array array) throws SQLException {
        if (array == null) {
            return null;
        }

        final Object object = array.getArray();
        if (object instanceof work_time[]) {
            return (work_time[]) object;
        } else if (object instanceof Object[]) {
            final Object[] objects = (Object[]) object;
            final work_time[] result = new work_time[objects.length];
            for (int i = 0; i < objects.length; i++) {
                result[i] = (work_time) objects[i];
            }
            return result;
        } else {
            throw new RuntimeException("Unsupported array object type: " + object.getClass());
        }
    }
}
