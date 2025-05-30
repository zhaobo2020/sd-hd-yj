package com.sl;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching // 必须添加此注解
@EnableScheduling // 启用定时任务
@EnableFeignClients
public class AppStart {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(AppStart.class, args);
    }
}
