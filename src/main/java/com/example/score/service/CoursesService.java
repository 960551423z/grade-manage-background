package com.example.score.service;

import com.example.score.common.PageInfoResult;
import com.example.score.common.ReturnPage;
import com.example.score.model.domain.Courses;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.score.model.dto.CourseDTO;
import com.example.score.model.dto.CourseLikeRequestDTO;


public interface CoursesService extends IService<Courses> {

    boolean addCourse(CourseDTO courseDTO);

    ReturnPage<Courses> getCourseByPage(PageInfoResult pageResult);

    ReturnPage<Courses> listByLikeCourse(CourseLikeRequestDTO courseLikeRequestDTO);
}
