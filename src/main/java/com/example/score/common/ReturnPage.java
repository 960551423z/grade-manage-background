package com.example.score.common;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 返回给前端的分页对象
 */
@Data
public class ReturnPage<T> implements Serializable {

    List<T> list;

    Long pageTotal;
}
