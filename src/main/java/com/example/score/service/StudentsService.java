package com.example.score.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.example.score.common.PageInfoResult;
import com.example.score.common.ReturnPage;
import com.example.score.model.domain.Students;
import com.example.score.model.dto.StudentsDTO;





public interface StudentsService extends IService<Students> {

    boolean addStudent(StudentsDTO studentsDTO);

    ReturnPage<Students> getStudentsByPage(PageInfoResult pageResult);

    ReturnPage<Students> listByName(String name);
}
