package com.example.score.common;

import com.example.score.model.domain.Courses;
import com.example.score.model.domain.Students;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 返回查询的学生姓名和课程名
 */
@Data
public class ReturnStudentAndCourse implements Serializable {

    private List<Students> studentsList;

    private List<Courses> coursesList;

}
