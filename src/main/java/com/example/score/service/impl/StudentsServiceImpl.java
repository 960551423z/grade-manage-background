package com.example.score.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.score.common.PageInfoResult;
import com.example.score.common.ReturnPage;
import com.example.score.model.domain.Students;

import com.example.score.handler.BaseException;
import com.example.score.mapper.StudentsMapper;
import com.example.score.model.dto.StudentsDTO;
import com.example.score.service.StudentsService;
import com.example.score.utils.SetDefaultValue;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Students>
    implements StudentsService {


    @Resource
    private StudentsMapper studentsMapper;


    @Override
    public boolean addStudent(StudentsDTO studentsDTO) {
        /**
         * ^ 表示字符串的开始。
         * 1 表示手机号码必须以1开头。
         * [3-9] 表示第二位必须是3到9之间的任意数字。
         * \\d{9} 表示接下来的9位必须是数字（0-9）。
         * $ 表示字符串的结束。
         */
        String regex = "^1[3-9]\\d{9}$";

        String phoneNumber = studentsDTO.getPhoneNumber();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new BaseException("手机号格式错误");
        }

        Students students = new Students();
        BeanUtils.copyProperties(studentsDTO,students);
        students.setCreateTime(new Date());
        students.setUpdateTime(new Date());

        boolean result = this.save(students);
        if (!result) {
            throw new BaseException("新增用户失败");
        }
        return true;
    }

    @Override
    public ReturnPage<Students> getStudentsByPage(PageInfoResult pageResult) {
        SetDefaultValue.setDefault(pageResult);
        Integer pageNum = pageResult.getPageNum();
        Integer current = pageResult.getCurrent();

        Page<Students> page = studentsMapper.selectPage(new Page<>(current, pageNum), null);
        List<Students> records = page.getRecords();
        long total = page.getTotal();
        ReturnPage<Students> returnPage = new ReturnPage<>();
        returnPage.setList(records);
        returnPage.setPageTotal(total);
        return returnPage;
    }

    @Override
    public ReturnPage<Students> listByName(String name) {
        LambdaQueryWrapper<Students> wrapper = new LambdaQueryWrapper<>();
        wrapper.likeRight(Students::getName,name);
        Page<Students> page = this.page(new Page<>(), wrapper);
        ReturnPage<Students> returnPage = new ReturnPage<>();
        returnPage.setList(page.getRecords());
        returnPage.setPageTotal(page.getTotal());
        return returnPage;
    }
}




