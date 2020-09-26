package com.shiro.service;

import com.entity.Result;
import serviceapi.entity.LkManager;
import com.baomidou.mybatisplus.extension.service.IService;
import serviceapi.dto.UserDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 后台管理用户表 服务类
 * </p>
 *
 * @author Nxy
 */
public interface LkManagerService extends IService<LkManager> {

    /**
     * 查询一个用户
     * @param userAccount 用户账号
     * @return LkManager
     */
    LkManager findByAccount(String userAccount);

    Result loginGetToken(UserDTO userDTO , HttpServletResponse response);
}
