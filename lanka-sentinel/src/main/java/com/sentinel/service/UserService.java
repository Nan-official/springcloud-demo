package com.sentinel.service;

import com.entity.Result;
import com.sentinel.service.impl.UserFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Nxy
 * @title: UserService
 * @projectName lanka
 * @description: TODO
 * @date 2020/9/25-7:02 下午
 */
@FeignClient(value = "lanka-service",fallback = UserFallbackService.class)
public interface UserService {
    @GetMapping("/{id}")
    Result getUserById(@PathVariable Long id);
}
