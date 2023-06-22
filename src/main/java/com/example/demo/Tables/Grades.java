package com.example.demo.Tables;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wangsang
 * @since 2023-04-12
 */
@TableName("grade")
@Data
public class Grades implements Serializable {

    private static final long serialVersionUID = 1L;
    private String SID;

    private String CID;

    private Integer SCORE;

    private String NOTE;


    public String getSID() {
        return SID;
    }
}

