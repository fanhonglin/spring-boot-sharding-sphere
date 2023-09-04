package com.example.springbootmongotest;


import com.oujiong.entity.User;
import com.oujiong.service.UserService;
import com.sun.glass.ui.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@SpringBootTest(classes = Application.class)
@Slf4j
public class QualityInspectInfoTest {

    @Resource
    private UserService userService;

    @Test
    public void insert() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < 500; i++) {
            List<User> resourceInfoList = new ArrayList<>(2000);
            for (int j = 0; j < 1000; j++) {
                User resourceInfo = new User();
                resourceInfo.setAge(j);
                resourceInfo.setName("fhl" + new Date().getTime());
                resourceInfo.setSex(String.valueOf(j / 2));
                resourceInfoList.add(resourceInfo);
            }
            semaphore.acquire();
            int finalI = i;
            executorService.submit(() -> {
                try {
                    StopWatch stopWatch = new StopWatch("第" + finalI + "个任务");
                    userService.insertForeach(resourceInfoList);
                    stopWatch.stop();
                    log.info("第" + finalI + "个任务任务花费的时间：{} 秒", stopWatch.getTotalTimeSeconds());
                } catch (Exception e) {

                } finally {
                    semaphore.release();
                }
            });
        }
    }
}
