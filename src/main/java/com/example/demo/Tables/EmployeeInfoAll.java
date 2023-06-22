package com.example.demo.Tables;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sun.source.doctree.SerialDataTree;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeInfoAll extends EmployeeInfo implements Serializable {
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
}
