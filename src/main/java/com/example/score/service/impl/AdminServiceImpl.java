package com.example.score.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.score.handler.BaseException;
import com.example.score.model.domain.Admin;
import com.example.score.model.dto.RegisterDTO;
import com.example.score.model.vo.AdminVO;
import com.example.score.service.AdminService;
import com.example.score.mapper.AdminMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{


    @Override
    public boolean register(RegisterDTO registerDTO) {
        String userAccount = registerDTO.getUserAccount();
        String userPassword = registerDTO.getUserPassword();
        String checkPassword = registerDTO.getCheckPassword();

        if (StringUtils.isBlank(userAccount) || StringUtils.isBlank(userPassword) || StringUtils.isBlank(checkPassword))
            throw new BaseException("传入参数存在空值");

        if (userAccount.length() < 4 || userAccount.length() > 12)
            throw new BaseException("用户名长度必须是4-12位");

        if (!userPassword.equals(checkPassword))
            throw new BaseException("两次密码不一致");

        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUserAccount,userAccount);
        Admin one = this.getOne(wrapper);
        if (one != null)
            throw new BaseException("用户已存在");

        String securePassword = SecureUtil.md5(userPassword);
        Admin admin = new Admin();
        BeanUtils.copyProperties(registerDTO,admin);
        admin.setName(userAccount);
        admin.setCreateTime(new Date());
        admin.setUpdateTime(new Date());
        admin.setUserPassword(securePassword);
        return this.save(admin);
    }

    @Override
    public AdminVO login(RegisterDTO registerDTO, HttpServletRequest request) {
        String userAccount = registerDTO.getUserAccount();
        String userPassword = registerDTO.getUserPassword();

        if (StringUtils.isBlank(userAccount) || StringUtils.isBlank(userPassword))
            throw new BaseException("传入参数存在空值");

        if (userAccount.length() < 4 || userAccount.length() > 12)
            throw new BaseException("用户名长度必须是4-12位");

        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        String securePassword = SecureUtil.md5(userPassword);
        wrapper.eq(Admin::getUserAccount,userAccount);
        wrapper.eq(Admin::getUserPassword,securePassword);
        Admin admin = this.getOne(wrapper);
        if (admin == null)
            throw new BaseException("用户或密码错误");


        // 登录成功，生成 UUID 作为token
        String token = UUID.randomUUID().toString(true);

        AdminVO adminVO = new AdminVO();
        BeanUtils.copyProperties(admin,adminVO);
        adminVO.setToken(token);
        request.getSession().setAttribute("user_login", adminVO);
        return adminVO;
    }
}




