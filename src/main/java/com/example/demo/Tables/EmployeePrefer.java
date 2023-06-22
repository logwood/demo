package com.example.demo.Tables;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author fc
 * @since 2023-03-24
 */
@TableName("employee_prefer")
public class EmployeePrefer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "staff_id", type = IdType.AUTO)
    private Integer staffId;

    private String prefer;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }


    @Override
    public String toString() {
        return "EmployeePrefer{" +
                ", staffId=" + staffId +
                "}";
    }

    public void setPrefer(String prefer) {
        this.prefer=prefer;
    }
}
