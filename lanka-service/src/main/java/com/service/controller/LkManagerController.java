package com.service.controller;


import com.entity.Result;
import com.service.service.LkManagerService;
import com.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import serviceapi.dto.UserDTO;
import serviceapi.entity.LkManager;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 后台管理用户表 前端控制器
 * </p>
 *
 * @author Nxy
 */
@RestController
@RequestMapping("/lk-service")
@Slf4j
public class LkManagerController extends BaseController{

    @Autowired
    private LkManagerService lkManagerService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID获取用户")
    public Result getUserById(@PathVariable Integer id) {

        log.info("端口号："+request.getServerPort());
        LkManager lkManager = lkManagerService.getById(id);
        System.out.println("获取的账户："+lkManager.getUserAccount());
        return ResultUtil.getInstance().setData(lkManager,"success");
    }
}
