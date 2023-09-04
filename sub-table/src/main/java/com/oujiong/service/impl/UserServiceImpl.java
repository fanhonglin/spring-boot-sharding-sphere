package com.oujiong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oujiong.entity.User;
import com.oujiong.mapper.UserMapper;
import com.oujiong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @Description: 用户实现类
 *
 * @author xub
 * @date 2019/10/10 下午8:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User>  implements UserService {

    @Override
    public void saveAll(List<User> resourceInfoList) {
        saveBatch(resourceInfoList);
    }
}
