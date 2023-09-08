package com.oujiong.controller;


import com.google.common.collect.Lists;
import com.oujiong.entity.User;
import com.oujiong.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 * @author xub
 * @Description: 接口测试
 * @date 2019/10/10 下午8:52
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * @Description: 批量保存用户
     */
    @GetMapping("save-user")
    public Object saveUser() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        Semaphore semaphore = new Semaphore(20);
        for (int i = 0; i < 20000; i++) {

            List<User> resourceInfoList = new ArrayList<>(20480);
            for (int j = 0; j < 10000; j++) {
                User resourceInfo = new User();

                // id 作为分表依据

                // 年龄字段作为分库
                resourceInfo.setAge(j);
                resourceInfo.setName("fhl" + new Date().getTime());
                resourceInfo.setSex(String.valueOf(j % 2));
                resourceInfo.setCreateTime(new Date());
                resourceInfo.setUpdateTime(new Date());
                resourceInfo.setStatus(j%2);
                resourceInfoList.add(resourceInfo);
            }
            semaphore.acquire();
            int finalI = i;
            executorService.submit(() -> {
                try {
                    StopWatch stopWatch = new StopWatch("第" + finalI + "个任务");
                    userService.saveAll(resourceInfoList);
                    stopWatch.stop();
                    log.info("第" + finalI + "个任务任务花费的时间：{} 秒", stopWatch.getTotalTimeSeconds());
                } catch (Exception e) {
                    log.error("插入异常：{}", e.getMessage(),e);
                } finally {
                    semaphore.release();
                }
            });
        }
        return null;
    }
    /**
     * @Description: 获取用户列表
     */
    @GetMapping("list-user")
    public Object listUser() {
//        return userService.list();
        return null;
    }

}
