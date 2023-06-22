package com.example.demo.Tables;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableId(value = "staff_id", type = IdType.AUTO)
    private Integer staffId;
    private String usercode;
    private Integer useriden;


    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public Integer getUseriden() {
        return useriden;
    }

    public void setUseriden(Integer useriden) {
        this.useriden = useriden;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "User{" +
                "staffId=" + staffId +
                ", usercode='" + usercode + '\'' +
                ", useriden=" + useriden +
                '}';
    }
}