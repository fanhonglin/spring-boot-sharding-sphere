package com.oujiong.service;

import com.oujiong.entity.User;

import java.util.List;

/**
 * @author xub
 * @Description: 用户相关接口
 * @date 2019/10/10 下午8:53
 */
public interface UserService {

    void saveAll(List<User> resourceInfoList);

//    Object list();
}