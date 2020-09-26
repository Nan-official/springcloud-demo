package com.sentinel.service.impl;

import com.entity.Result;
import com.sentinel.service.UserService;
import com.utils.ResultUtil;
import org.springframework.stereotype.Component;
import serviceapi.entity.LkManager;

/**
 * @author Nxy
 * @title: UserFallbackService
 * @projectName lanka
 * @description: TODO
 * @date 2020/9/25-6:59 下午
 */
@Component
public class UserFallbackService implements UserService {

    @Override
    public Result getUserById(Long id) {
        LkManager defaultUser = new LkManager();
        defaultUser.setUserAccount("defaultUser");
        defaultUser.setPassword("123456");
        return ResultUtil.getInstance().setData(defaultUser,"服务降级返回");
    }
}
