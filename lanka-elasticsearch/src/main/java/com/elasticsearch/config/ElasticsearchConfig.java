package com.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nxy
 * @title: ElasticsearchConfig
 * @projectName lanka
 * @description: TODO
 */
@Configuration
public class ElasticsearchConfig {
    @Bean
    public RestHighLevelClient esRestHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                // 这里可以配置多个 es服务，我当前服务不是集群，所以目前只配置一个
                RestClient.builder(
                        new HttpHost("192.168.50.10", 9200, "http")));

        return client;
    }
}
