package com.example.score.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回给前端
 */
@Data
public class AdminVO implements Serializable {

    private Integer id;

    /**
     *
     */
    private String name;

    /**
     * 用户名
     */
    private String userAccount;



    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 0-女 1-男
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;


    /**
     * token
     */
    private String token;


}
