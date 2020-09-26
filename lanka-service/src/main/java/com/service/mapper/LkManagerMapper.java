package com.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import serviceapi.entity.LkManager;

/**
 * <p>
 * 后台管理用户表 Mapper 接口
 * </p>
 *
 * @author Nxy
 */
public interface LkManagerMapper extends BaseMapper<LkManager> {

    LkManager findByAccount(String userAccount);

    LkManager selectById(Integer id);

}
