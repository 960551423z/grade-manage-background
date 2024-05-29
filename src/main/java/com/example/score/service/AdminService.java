package com.example.score.service;

import com.example.score.model.domain.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.score.model.dto.RegisterDTO;
import com.example.score.model.vo.AdminVO;

import javax.servlet.http.HttpServletRequest;


public interface AdminService extends IService<Admin> {

    boolean register(RegisterDTO registerDTO);

    AdminVO login(RegisterDTO registerDTO, HttpServletRequest request);
}
