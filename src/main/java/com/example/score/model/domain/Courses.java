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
 * @TableName Courses
 */
@TableName(value ="Courses")
@Data
public class Courses implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer courseID;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 授课老师
     */
    private String instructor;

    /**
     * 学分
     */
    private Integer credits;

    /**
     * 学期
     */
    private String semester;

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