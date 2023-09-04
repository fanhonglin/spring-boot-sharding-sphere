package com.oujiong.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * user表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 主键
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     *
     */
    @Builder.Default
    private Date createTime = new Date();

    /**
     *
     */
    @Builder.Default
    private Date updateTime = new Date();

    /**
     * 是否删除 1删除 0未删除
     */
    @Builder.Default
    private Integer status = 0;

//    public User(Long id,String name, String sex, Integer age) {
//        this.id = id;
//        this.name = name;
//        this.sex = sex;
//        this.age = age;
//    }
}
