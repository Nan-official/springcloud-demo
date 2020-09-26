package com.shiro.service.impl;

import com.constant.RedisConstant;
import com.constant.ResultCode;
import com.entity.Result;
import com.redis.util.RedisUtil;
import com.shiro.utils.JwtUtil;
import com.utils.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import serviceapi.entity.LkManager;
import com.shiro.mapper.LkManagerMapper;
import com.shiro.service.LkManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import serviceapi.dto.UserDTO;

import javax.servlet.http.HttpServletResponse;

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

    @Value("${config.refreshToken-expireTime}")
    private String refreshTokenExpireTime;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * session中的验证码
     */
    private String SHIRO_VERIFY_SESSION = "verifySessionCode";


    @Override
    public LkManager findByAccount(String userAccount) {
        return lkManagerMapper.findByAccount(userAccount);
    }

    /**
     * 登录获取Token
     * @param userDTO
     * @param response
     * @return
     */
    @Override
    public Result loginGetToken(UserDTO userDTO, HttpServletResponse response) {
        if (userDTO == null || StringUtils.isBlank(userDTO.getPassword()) || StringUtils.isBlank(userDTO.getUserName())){
            return ResultUtil.getInstance().setErrorMsg(ResultCode.ERROR, "账户或密码不得为空!");
        }

        Subject currentUser = SecurityUtils.getSubject();

        // 获取session中的验证码
        String verCode = (String) currentUser.getSession().getAttribute(SHIRO_VERIFY_SESSION);

//        if (StringUtils.isBlank(verifyCode) || !StringUtils.equals(verCode, verifyCode)) {
//            return ResultUtil.getInstance().setErrorMsg(ResultCode.ERROR, "验证码输入错误");
//        }

        LkManager lkManager = this.findByAccount(userDTO.getUserName());
        if (lkManager == null){
            return ResultUtil.getInstance().setErrorMsg(ResultCode.NOT_FOUND, "用户不存在!");
        }
        String userAccount = lkManager.getUserAccount();
//        String encodePassword = ShiroKit.md5(userDTO.getPassword(), lkManager.getSalt());
//        if (!encodePassword.equals(lkManager.getPassword())) {
//            return ResultUtil.getInstance().setErrorMsg(ResultCode.ERROR, "密码错误!");
//        }
        try {

            // 清除可能存在的shiro权限信息缓存
            if (redisUtil.hasKey(RedisConstant.PREFIX_SHIRO_CACHE + userAccount)) {
                redisUtil.del(RedisConstant.PREFIX_SHIRO_CACHE + userAccount);
            }

            // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
            String currentTimeMillis = String.valueOf(System.currentTimeMillis());
            redisUtil.set(RedisConstant.PREFIX_SHIRO_REFRESH_TOKEN + userAccount, currentTimeMillis,
                    Integer.parseInt(refreshTokenExpireTime));

            // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
            String jwt = JwtUtil.sign(userAccount, currentTimeMillis);
            response.setHeader("Authorization", jwt);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");

            return ResultUtil.getInstance().setSuccessMsg("登录成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.getInstance().setErrorMsg(ResultCode.ERROR, e.getMessage());
        }
    }
}
