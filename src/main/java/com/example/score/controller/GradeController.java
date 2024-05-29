package com.example.score.controller;


import com.example.score.common.Result;
import com.example.score.common.ReturnPage;
import com.example.score.common.ReturnStudentAndCourse;
import com.example.score.handler.BaseException;
import com.example.score.model.domain.Grades;
import com.example.score.model.dto.GradesDTO;

import com.example.score.model.vo.StudentAndCourseVO;
import com.example.score.service.GradesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/grade")
@Api("成绩接口")
public class GradeController {

    @Resource
    private GradesService gradesService;



    @PostMapping("/add")
    @ApiOperation("新增成绩记录")
    public Result addGrade(@RequestBody GradesDTO gradesDTO) {
        if (gradesDTO == null)
            throw new BaseException("请求参数为空");
        boolean result = gradesService.addGrade(gradesDTO);
        return Result.success(result);
    }


    @GetMapping("/all")
    @ApiOperation("查询学生和课程的列表")
    public Result listAll() {
        ReturnStudentAndCourse listAll = gradesService.listAll();
        return Result.success(listAll);
    }


    @GetMapping("/delete")
    @ApiOperation("删除成绩记录")
    public Result delete(@RequestParam Integer id) {
        if (id == null || id <= 0)
            throw new BaseException("请求参数异常");
        boolean result = gradesService.removeById(id);
        return Result.success(result);
    }


    @PostMapping("/update")
    @ApiOperation("更新成绩记录")
    public Result update(@RequestBody GradesDTO gradesDTO) {
        if (gradesDTO == null)
            throw new BaseException("请求参数为空");
        Grades grades = new Grades();
        BeanUtils.copyProperties(gradesDTO,grades);
        boolean result = gradesService.updateById(grades);
        return Result.success(result);
    }


    @GetMapping("/list")
    @ApiOperation("成绩记录查询")
    public Result list() {
        List<StudentAndCourseVO> list = gradesService.listByPackage();
        ReturnPage<StudentAndCourseVO> page = new ReturnPage<>();
        page.setList(list);
        int size = list.size();
        page.setPageTotal((long) size);
        return Result.success(page);
    }


    @GetMapping("/search")
    @ApiOperation("条件查询")
    public Result search(@RequestParam String name,
                         @RequestParam String courseName) {

        List<StudentAndCourseVO> list = gradesService.listBySearch(name,courseName);
        ReturnPage<StudentAndCourseVO> page = new ReturnPage<>();
        page.setList(list);
        int size = list.size();
        page.setPageTotal((long) size);
        return Result.success(page);
    }
}
