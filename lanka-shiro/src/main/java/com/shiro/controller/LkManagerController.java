package com.shiro.controller;


import com.constant.JwtConstant;
import com.constant.RedisConstant;
import com.constant.ResultCode;
import com.entity.Result;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.redis.util.RedisUtil;
import com.shiro.service.LkManagerService;
import com.shiro.utils.JwtUtil;
import com.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import serviceapi.entity.LkManager;
import serviceapi.dto.UserDTO;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;

/**
 * <p>
 * 后台管理用户表 前端控制器
 * </p>
 *
 * @author Nxy
 */
@RestController
@RequestMapping("/lkManager")
public class LkManagerController extends BaseController {

    @Value("${config.refreshToken-expireTime}")
    private String refreshTokenExpireTime;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private LkManagerService lkManagerService;

    /**
     * session中的验证码
     */
    private String SHIRO_VERIFY_SESSION = "verifySessionCode";

    @PostMapping("/login")
    @ApiOperation(value = "登录，发放token")
    public Result login(@RequestBody UserDTO userDTO, HttpServletResponse response) {
       return lkManagerService.loginGetToken(userDTO,response);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出登录，清除token")
    public Result logout() {
        try {
            String token = "";
            // 获取头部信息
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                if ("Authorization".equalsIgnoreCase(key)) {
                    token = value;
                }
            }
            // 校验token
            if (StringUtils.isBlank(token)) {
                return ResultUtil.getInstance().setErrorMsg(ResultCode.PARAM_ERROR, "未登录");
            }
            String account = JwtUtil.getClaim(token, JwtConstant.ACCOUNT);
            if (StringUtils.isBlank(account)) {
                return ResultUtil.getInstance().setErrorMsg(ResultCode.NOT_LOGIN, "token失效或不正确.");
            }
            // 清除shiro权限信息缓存
            if (redisUtil.hasKey(RedisConstant.PREFIX_SHIRO_CACHE + account)) {
                redisUtil.del(RedisConstant.PREFIX_SHIRO_CACHE + account);
            }
            // 清除RefreshToken
            redisUtil.del(RedisConstant.PREFIX_SHIRO_REFRESH_TOKEN + account);

            return ResultUtil.getInstance().setSuccessMsg("登出成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.getInstance().setErrorMsg(ResultCode.ERROR, e.getMessage());
        }
    }

    @PostMapping("/current")
    @ApiOperation(value = "获取当前登录用户")
    public Result current() {
        try {
            LkManager lkManager = new LkManager();
            Subject subject = SecurityUtils.getSubject();
            if (subject != null) {
                String token = (String) subject.getPrincipal();
                if (StringUtils.isNotBlank(token)) {
                    String account = JwtUtil.getClaim(token, JwtConstant.ACCOUNT);
                    if (StringUtils.isNotBlank(account)) {
                        lkManager = lkManagerService.findByAccount(account);
                    }
                }
            }
            return new ResultUtil<LkManager>().setMsg(ResultCode.SUCCESS, "success", lkManager);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultUtil<LkManager>().setErrorMsg(ResultCode.ERROR, e.getMessage());
        }
    }

    /**
     * 获取验证码
     *
     * @param response
     */
    @GetMapping("/getCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        byte[] verByte = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute(SHIRO_VERIFY_SESSION, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        verByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(verByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

}
