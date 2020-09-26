package com.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Nxy
 * @title: ElasticsearchApplication
 * @projectName lanka
 * @description: TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ElasticsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class,args);
    }
}
