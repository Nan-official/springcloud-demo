package com.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Nxy
 * @title: OpenFeignApplication
 * @projectName nacos
 * @description: TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OpenFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignApplication.class,args);
    }
}
