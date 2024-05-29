package com.example.score.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 注册的字段
 */

@Data
public class RegisterDTO implements Serializable {

    /**
     * 用户名
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 重复校验密码
     */
    private String checkPassword;

}
