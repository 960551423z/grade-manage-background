package com.example.score.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.score.common.PageInfoResult;
import com.example.score.common.Result;
import com.example.score.common.ReturnPage;
import com.example.score.handler.BaseException;
import com.example.score.model.domain.Students;
import com.example.score.model.dto.StudentsDTO;
import com.example.score.service.StudentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/student")
@Api("学生接口")
public class StudentController {

    @Resource
    private StudentsService studentsService;

    @PostMapping("/add")
    @ApiOperation("新增学生")
    public Result addStudent(@RequestBody StudentsDTO studentsDTO) {
        if (studentsDTO == null)
            throw new BaseException("请求参数为空");
        boolean result = studentsService.addStudent(studentsDTO);
        return Result.success(result);
    }


    @PostMapping("/list")
    @ApiOperation("分页查询")
    public Result listStudent(@RequestBody PageInfoResult pageResult) {
        if (pageResult == null) {
            throw new BaseException("请求参数为空");
        }
        ReturnPage<Students> page = studentsService.getStudentsByPage(pageResult);
        return Result.success(page);
    }


    @GetMapping("/list/search")
    @ApiOperation("分页查询")
    public Result listByNameStudent(@RequestParam String name) {

        ReturnPage<Students> page = studentsService.listByName(name);
        return Result.success(page);
    }


    @GetMapping("/delete")
    @ApiOperation("删除学生")
    public Result deleteStudent(@RequestParam Integer id) {
        if (id == null || id <= 0 )
            throw new BaseException("请求ID为空");
        boolean result = studentsService.removeById(id);
        return Result.success(result);
    }


    @PostMapping("/update")
    @ApiOperation("更新学生")
    public Result updateStudent(@RequestBody StudentsDTO studentsDTO) {
        if (studentsDTO == null)
            throw new BaseException("请求参数为空");
        Integer id = studentsDTO.getStudentID();
        if (id == null || id <= 0 )
            throw new BaseException("Id参数异常");
        Students students = new Students();
        BeanUtils.copyProperties(studentsDTO,students);
        boolean result = studentsService.updateById(students);
        return Result.success(result);
    }
}
