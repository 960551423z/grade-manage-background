package com.example.score.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName Students
 */
@TableName(value ="Students")
@Data
public class Students implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}