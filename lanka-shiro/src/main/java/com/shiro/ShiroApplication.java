package com.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Nxy
 * @title: ShiroApplication
 * @projectName lanka
 * @description: TODO
 */
@EnableDiscoveryClient
@RefreshScope
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"com.redis","com.shiro"})
@MapperScan({"com.shiro.mapper"})
public class ShiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class,args);
    }
}

