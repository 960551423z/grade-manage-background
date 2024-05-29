package com.example.score.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回给前端封装好的数据
 */
@Data
public class StudentAndCourseVO implements Serializable {

    /**
     * 课程ID
     */
    private Integer courseID;

    /**
     * 课程名
     */
    private String courseName;


    /**
     * 学生ID
     */
    private Integer studentID;

    /**
     * 姓名
     */
    private String name;

    /**
     * 成绩记录的Id
     */
    private Integer gradeID;

    /**
     * 成绩
     */
    private String grade;

    /**
     * 学期
     */
    private String semester;

}
