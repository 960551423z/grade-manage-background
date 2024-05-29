package com.example.score.common;


import lombok.Data;

import java.io.Serializable;

@Data
public class PageInfoResult implements Serializable {
    // 当前页数
    private Integer current;

    // 每页显示条数
    private Integer pageNum;


}
