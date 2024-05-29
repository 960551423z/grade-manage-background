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
 * @TableName Grades
 */
@TableName(value ="Grades")
@Data
public class Grades implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer gradeID;

    /**
     * 学生Id
     */
    private Integer studentID;

    /**
     * 课程Id
     */
    private Integer courseID;

    /**
     * 成绩
     */
    private String grade;

    /**
     * 学期
     */
    private String semester;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}