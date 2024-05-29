package com.example.score.model.dto;



import lombok.Data;

import java.io.Serializable;


@Data
public class CourseDTO implements Serializable {
    /**
     * id
     */
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
}
