package com.shiro.mapper;

import serviceapi.entity.LkManager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 后台管理用户表 Mapper 接口
 * </p>
 *
 * @author Nxy
 */
public interface LkManagerMapper extends BaseMapper<LkManager> {

    LkManager findByAccount(String userAccount);

}
