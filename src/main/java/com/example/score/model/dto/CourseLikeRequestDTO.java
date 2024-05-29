package com.example.score.model.dto;

import com.example.score.common.PageInfoResult;
import lombok.Data;

import java.io.Serializable;


@Data
public class CourseLikeRequestDTO implements Serializable {
    /**
     * 课程名
     */
    private String courseName;

    /**
     * 授课老师
     */
    private String instructor;


    /**
     * 学期
     */
    private String semester;

}
