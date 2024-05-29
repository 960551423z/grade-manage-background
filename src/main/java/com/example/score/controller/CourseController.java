package com.example.score.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.score.common.PageInfoResult;
import com.example.score.common.Result;
import com.example.score.common.ReturnPage;
import com.example.score.handler.BaseException;
import com.example.score.model.domain.Courses;
import com.example.score.model.domain.Students;
import com.example.score.model.dto.CourseDTO;
import com.example.score.model.dto.CourseLikeRequestDTO;
import com.example.score.service.CoursesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/course")
@Api("课程接口")
public class CourseController {


    @Resource
    private CoursesService coursesService;

    @PostMapping("/add")
    @ApiOperation("新增课程")
    public Result addCourse(@RequestBody CourseDTO courseDTO) {
        if (courseDTO == null)
            throw new BaseException("请求参数错误");

        boolean result = coursesService.addCourse(courseDTO);
        return Result.success(result);
    }


    @GetMapping("/delete")
    @ApiOperation("删除课程")
    public Result deleteCourse(@RequestParam Integer id) {
        // todo： 课程删除，对应的课程成绩关系，应该也进行删除
        if (id == null || id <= 0 )
            throw new BaseException("请求ID为空");
        boolean result = coursesService.removeById(id);
        return Result.success(result);
    }

    @PostMapping("/list")
    @ApiOperation("分页查询")
    public Result listCourse(@RequestBody PageInfoResult pageResult) {
        if (pageResult == null) {
            throw new BaseException("请求参数为空");
        }
        ReturnPage<Courses> page = coursesService.getCourseByPage(pageResult);
        return Result.success(page);
    }

    @PostMapping("/search")
    @ApiOperation("分页条件查询")
    public Result listByLikeCourse(@RequestBody CourseLikeRequestDTO courseLikeRequestDTO) {
        ReturnPage<Courses> page = coursesService.listByLikeCourse(courseLikeRequestDTO);
        return Result.success(page);
    }



    @PostMapping("/update")
    @ApiOperation("课程修改")
    public Result updateCourse(@RequestBody CourseDTO courseDTO) {
        if (courseDTO == null) {
            throw new BaseException("请求参数为空");
        }
        Courses courses = new Courses();
        BeanUtils.copyProperties(courseDTO,courses);
        boolean result = coursesService.updateById(courses);
        return Result.success(result);
    }


}
