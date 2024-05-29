package com.example.score.model.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 前端传给后端的值
 */

@Data
public class StudentsDTO implements Serializable {

    /**
     * id
     */
    private Integer studentID;

    /**
     * 姓名
     */
    private String name;


    /**
     * 性别
     */
    private Integer gender;

    /**
     * 班级
     */
    private String className;

    /**
     * 手机号
     */
    private String phoneNumber;

}
