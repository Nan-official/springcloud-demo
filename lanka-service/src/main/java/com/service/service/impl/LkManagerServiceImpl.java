package com.service.service.impl;

import com.service.mapper.LkManagerMapper;
import com.service.service.LkManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviceapi.entity.LkManager;

import java.io.Serializable;

/**
 * <p>
 * 后台管理用户表 服务实现类
 * </p>
 *
 * @author Nxy
 */
@Service
public class LkManagerServiceImpl extends ServiceImpl<LkManagerMapper, LkManager> implements LkManagerService {

    @Autowired
    private LkManagerMapper lkManagerMapper;

    @Override
    public LkManager findByAccount(String userAccount) {
        return null;
    }

    @Override
    public LkManager getById(Serializable id) {
        return lkManagerMapper.selectById(id);
    }
}
