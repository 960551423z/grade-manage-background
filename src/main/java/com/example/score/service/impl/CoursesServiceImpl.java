package com.example.score.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.score.common.PageInfoResult;
import com.example.score.common.ReturnPage;
import com.example.score.handler.BaseException;
import com.example.score.model.domain.Courses;
import com.example.score.model.dto.CourseDTO;
import com.example.score.model.dto.CourseLikeRequestDTO;
import com.example.score.service.CoursesService;
import com.example.score.mapper.CoursesMapper;
import com.example.score.utils.SetDefaultValue;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class CoursesServiceImpl extends ServiceImpl<CoursesMapper, Courses>
    implements CoursesService{


    @Override
    public boolean addCourse(CourseDTO courseDTO) {

        Courses courses = new Courses();
        BeanUtils.copyProperties(courseDTO,courses);
        courses.setCreateTime(new Date());
        courses.setUpdateTime(new Date());
        boolean result = this.save(courses);

        if (!result) {
            throw new BaseException("新增失败");
        }
        return true;
    }


    @Override
    public ReturnPage<Courses> getCourseByPage(PageInfoResult pageResult) {
        SetDefaultValue.setDefault(pageResult);
        Integer pageNum = pageResult.getPageNum();
        Integer current = pageResult.getCurrent();
        Page<Courses> page = this.page(new Page<>(current, pageNum), null);
        ReturnPage<Courses> result = new ReturnPage<>();
        result.setList(page.getRecords());
        result.setPageTotal(page.getTotal());
        return result;
    }

    @Override
    public ReturnPage<Courses> listByLikeCourse(CourseLikeRequestDTO courseLikeRequestDTO) {
        LambdaQueryWrapper<Courses> wrapper = new LambdaQueryWrapper<>();
        String courseName = courseLikeRequestDTO.getCourseName();
        String instructor = courseLikeRequestDTO.getInstructor();
        String semester = courseLikeRequestDTO.getSemester();
        wrapper.likeRight(Courses::getCourseName,courseName);
        wrapper.likeRight(Courses::getInstructor,instructor);
        wrapper.eq(Courses::getSemester,semester);
        Page<Courses> page = this.page(new Page<>(), wrapper);
        ReturnPage<Courses> result = new ReturnPage<>();
        result.setList(page.getRecords());
        result.setPageTotal(page.getTotal());
        return result;
    }
}




