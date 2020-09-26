package com.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Nxy
 * @title: com.service.LankaServiceApplication
 * @projectName lanka
 * @description: TODO
 */
@EnableDiscoveryClient
@RefreshScope
@EnableSwagger2
@SpringBootApplication
@MapperScan({"com.service.mapper"})
public class LankaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LankaServiceApplication.class,args);
    }
}
