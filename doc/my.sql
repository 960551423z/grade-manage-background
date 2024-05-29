-- 创建数据库
CREATE DATABASE StudentManagementSystem
    CHARACTER SET utf8
    COLLATE utf8_general_ci;

-- 使用数据库
USE StudentManagementSystem;

-- 创建用户登录表
create table Admin
(
    id    int auto_increment
        primary key,
    Name         varchar(100)                       not null,
    UserAccount  varchar(100)                       not null comment '用户名',
    UserPassword varchar(100)                       not null comment '密码',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    Gender       int                                null comment '0-女 1-男',
    phone        varchar(128)                       null comment '手机号',
    email        varchar(512)                       null comment '邮箱',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete     tinyint  default 0                 not null comment '是否删除'
);


-- 创建学生表（Students）
CREATE TABLE Students (
                          StudentID INT AUTO_INCREMENT PRIMARY KEY comment 'id',
                          Name VARCHAR(100) NOT NULL comment '姓名',
                          DateOfBirth DATETIME NOT NULL comment '出生日期',
                          Gender INT NOT NULL default 0 comment '性别',
                          ClassName VARCHAR(50) NOT NULL comment '班级',
                          PhoneNumber VARCHAR(20) comment '手机号',
                          createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
                          updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
                          isDelete     tinyint  default 0                 not null comment '是否删除'
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 创建课程表（Courses）
CREATE TABLE Courses (
                         CourseID INT AUTO_INCREMENT PRIMARY KEY comment 'id',
                         CourseName VARCHAR(100)  comment '课程名',
                         Instructor VARCHAR(100) comment '授课老师',
                         Credits INT comment '学分',
                         Semester VARCHAR(20)  comment '学期',
                         createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
                         updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
                         isDelete     tinyint  default 0                 not null comment '是否删除'
) CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 创建成绩表（Grades）
CREATE TABLE Grades (
                        GradeID INT AUTO_INCREMENT PRIMARY KEY,
                        StudentID INT NOT NULL comment '学生Id',
                        CourseID INT NOT NULL comment '课程Id',
                        Grade VARCHAR(3) NOT NULL comment '成绩',
                        Semester VARCHAR(20) NOT NULL comment '学期',
                        createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
                        updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
                        isDelete     tinyint  default 0                 not null comment '是否删除'
) CHARACTER SET utf8 COLLATE utf8_general_ci;
