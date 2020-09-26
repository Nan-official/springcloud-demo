package com.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import serviceapi.entity.LkManager;

/**
 * <p>
 * 后台管理用户表 服务类
 * </p>
 *
 * @author Nxy
 */
@Service
public interface LkManagerService extends IService<LkManager> {
    /**
     * 查询一个用户
     * @param userAccount 用户账号
     * @return LkManager
     */
    LkManager findByAccount(String userAccount);
}
