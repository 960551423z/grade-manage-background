package com.example.score.model.dto;


import lombok.Data;
import java.io.Serializable;

@Data
public class GradesDTO implements Serializable {

    /**
     * 成绩ID
     */
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

}