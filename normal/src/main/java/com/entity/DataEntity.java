package com.entity;

import lombok.Data;

/**
 * @author:YiXia
 * @create: 2022-11-16 22:04:40
 * @Description: 实体类
 */


@Data
public class DataEntity {
    //内容
    private String value;
    //字体颜色
    private String color;

    public DataEntity(String value, String color) {
        this.value = value;
        this.color = color;
    }
}
