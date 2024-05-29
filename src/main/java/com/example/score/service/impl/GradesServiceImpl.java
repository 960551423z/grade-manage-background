package com.example.score.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.score.common.ReturnStudentAndCourse;
import com.example.score.handler.BaseException;
import com.example.score.mapper.CoursesMapper;
import com.example.score.mapper.StudentsMapper;
import com.example.score.model.domain.Courses;
import com.example.score.model.domain.Grades;
import com.example.score.model.domain.Students;
import com.example.score.model.dto.GradesDTO;
import com.example.score.model.vo.StudentAndCourseVO;
import com.example.score.service.GradesService;
import com.example.score.mapper.GradesMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class GradesServiceImpl extends ServiceImpl<GradesMapper, Grades>
    implements GradesService{

    @Resource
    private CoursesMapper coursesMapper;

    @Resource
    private StudentsMapper studentsMapper;


    @Override
    public boolean addGrade(GradesDTO gradesDTO) {
        Grades grades = new Grades();
        BeanUtils.copyProperties(gradesDTO,grades);
        grades.setCreateTime(new Date());
        grades.setUpdateTime(new Date());
        boolean result = this.save(grades);
        if (!result) {
            throw new BaseException("新增成绩错误");
        }
        return true;
    }

    @Override
    public ReturnStudentAndCourse listAll() {

        // 1.先查询所有的学生姓名和ID
        LambdaQueryWrapper<Students> wrapperStudent = new LambdaQueryWrapper<>();
        wrapperStudent.select(Students::getName,Students::getStudentID);
        List<Students> students = studentsMapper.selectList(wrapperStudent);

        // 2. 查询所有课程名和ID
        LambdaQueryWrapper<Courses> wrapperCourse = new LambdaQueryWrapper<>();
        wrapperCourse.select(Courses::getCourseName,Courses::getCourseID);
        List<Courses> courses = coursesMapper.selectList(wrapperCourse);

        ReturnStudentAndCourse andCourse = new ReturnStudentAndCourse();
        andCourse.setStudentsList(students);
        andCourse.setCoursesList(courses);
        return andCourse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<StudentAndCourseVO> listByPackage() {
        List<Grades> list = this.list();
        ArrayList<StudentAndCourseVO> newList = new ArrayList<>();
        for(Grades grade : list) {
            Integer studentID = grade.getStudentID();
            Integer courseID = grade.getCourseID();
            Courses courses = coursesMapper.selectById(courseID);
            String courseName = courses.getCourseName();
            Students students = studentsMapper.selectById(studentID);
            String name = students.getName();
            StudentAndCourseVO studentAndCourseVO = new StudentAndCourseVO();
            BeanUtils.copyProperties(grade, studentAndCourseVO);
            studentAndCourseVO.setCourseName(courseName);
            studentAndCourseVO.setName(name);
            newList.add(studentAndCourseVO);
        }
        return newList;
    }

    @Override
    public List<StudentAndCourseVO> listBySearch(String name, String courseName) {
        List<Grades> list = this.list();
        ArrayList<StudentAndCourseVO> newList = new ArrayList<>();
        for(Grades grade : list) {
            Integer studentID = grade.getStudentID();
            Integer courseID = grade.getCourseID();
            Courses courses = coursesMapper.selectById(courseID);
            String courseName1 = courses.getCourseName();
            Students students = studentsMapper.selectById(studentID);
            String name1 = students.getName();
            StudentAndCourseVO studentAndCourseVO = new StudentAndCourseVO();
            BeanUtils.copyProperties(grade, studentAndCourseVO);
            studentAndCourseVO.setCourseName(courseName1);
            studentAndCourseVO.setName(name1);
            newList.add(studentAndCourseVO);
        }


        // 如果 name 或 courseName 存在，则过滤 newList
        if ((name != null && !name.isEmpty()) || (courseName != null && !courseName.isEmpty())) {
            return newList.stream()
                    .filter(studentAndCourseVO ->
                            (name != null && !name.isEmpty() && studentAndCourseVO.getName().contains(name)) ||
                                    (courseName != null && !courseName.isEmpty() && studentAndCourseVO.getCourseName().contains(courseName)))
                    .collect(Collectors.toList());
        }
        return newList;
    }
}




