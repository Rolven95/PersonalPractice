package com.github.rolven95.database.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserGraphServiceTest {
    @Autowired
    UserGraphService userGraphService;

    @Test
    void context() {
    }

    @Test
    void createUser() {
        CountDownLatch countDownLatch = new CountDownLatch(14);
        long startTime = System.currentTimeMillis();
        for (int thread = 0; thread < 40; thread++) {
            int finalThread = thread;
            new Thread(new Runnable() {
                final int threadId = finalThread;
                int baseUuid = (threadId + 50) * 1000;

                @Override
                public void run() {
                    for (int user = 0; user < 1000; user++) {
                        int uuid = baseUuid + user;
                        String mobile = "135" + uuid;
                        String ctCode = "86";
                        userGraphService.createUser(uuid, mobile, ctCode);
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await();
            long endTime = System.currentTimeMillis();
            log.debug("All writing done, timeCost:{}", endTime - startTime);
            System.out.println("All writing done, timeCost:{}" + (endTime - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}