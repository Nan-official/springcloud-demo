package com.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * @author Nxy
 * @title: LankaBlockRequestHandler
 * @projectName lanka
 * @description: TODO
 */
public class LankaBlockRequestHandler implements BlockRequestHandler {

        @Override
        public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable ex) {

            // 返回http状态码为200
            return ServerResponse.status(200).contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue("当前访问人数太多"));
        }

    }
