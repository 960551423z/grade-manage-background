package com.example.score.service;

import com.example.score.common.ReturnStudentAndCourse;
import com.example.score.model.domain.Grades;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.score.model.dto.GradesDTO;
import com.example.score.model.vo.StudentAndCourseVO;

import java.util.List;


public interface GradesService extends IService<Grades> {

    boolean addGrade(GradesDTO gradesDTO);

    ReturnStudentAndCourse listAll();

    List<StudentAndCourseVO> listByPackage();

    List<StudentAndCourseVO> listBySearch(String name, String courseName);
}
