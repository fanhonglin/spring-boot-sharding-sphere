package com.oujiong.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oujiong.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 用户mapper
 *
 * @author xub
 * @date 2019/10/10 下午8:52
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}