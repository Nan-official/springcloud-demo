package com.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Nxy
 * @title: SentinelServiceApplication
 * @projectName lanka
 * @description: TODO
 * @date 2020/9/25-10:36 下午
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelServiceApplication.class, args);
    }
}
