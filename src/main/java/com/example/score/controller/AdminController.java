package com.example.score.controller;


import com.example.score.common.Result;
import com.example.score.handler.BaseException;
import com.example.score.model.dto.RegisterDTO;
import com.example.score.model.vo.AdminVO;
import com.example.score.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录，注册
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("/register")
    @ApiOperation("注册")
    public Result register(@RequestBody RegisterDTO registerDTO) {
        if (registerDTO == null)
            throw new BaseException("请求参数为空");

        boolean result = adminService.register(registerDTO);
        return Result.success(result);
    }


    @PostMapping("/login")
    @ApiOperation("登录")
    public Result login(@RequestBody RegisterDTO registerDTO, HttpServletRequest request) {
        if (registerDTO == null)
            throw new BaseException("请求参数为空");

        AdminVO result = adminService.login(registerDTO,request);
        return Result.success(result);
    }

}
