package com.shiro.filter;


import com.constant.JwtConstant;
import com.constant.RedisConstant;
import com.redis.util.RedisUtil;
import com.shiro.service.LkManagerService;
import com.shiro.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import serviceapi.entity.LkManager;

/**
 * @author Nxy
 * @title: ManagerRealm
 * @projectName lanka
 * @description: TODO
 */
public class ManagerRealm extends AuthorizingRealm {


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private LkManagerService lkManagerService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (null == authenticationToken){
            throw new AuthenticationException("Token不能为空");
        }
        String token = (String)authenticationToken.getPrincipal();
        String userAccount  = JwtUtil.getClaim(token,JwtConstant.ACCOUNT);
        LkManager lkManager = lkManagerService.findByAccount(userAccount);
        if (lkManager == null) {
            throw new AuthenticationException("该帐号不存在");
        }
        // 开始认证，要AccessToken认证通过，且Redis中存在RefreshToken，且两个Token时间戳一致
        if (JwtUtil.verify(token) && redisUtil.hasKey(RedisConstant.PREFIX_SHIRO_REFRESH_TOKEN + userAccount)) {
            // 获取RefreshToken的时间戳
            String currentTimeMillisRedis = redisUtil.get(RedisConstant.PREFIX_SHIRO_REFRESH_TOKEN + userAccount).toString();
            // 获取AccessToken时间戳，与RefreshToken的时间戳对比
            if (JwtUtil.getClaim(token, JwtConstant.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)) {
                return new SimpleAuthenticationInfo(token, token, getName());
            }
        }
        throw new AuthenticationException("令牌过期或不正确!");
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        /*
        String account = JwtUtil.getClaim(principals.toString(), SecurityConsts.ACCOUNT);
		LkManager lkManager = lkManagerService.findByAccount(userAccount);

		//获取role
		List<Role> RoleList = roleService.findRoleByUserId(lkManager.getId());
		//获取权限
		List<Object> AuthorityList = authorityService.findByUserId(lkManager.getId());
		for(Role Role : RoleList){
			simpleAuthorizationInfo.addRole(Role.getName());
			for(Object auth: AuthorityList){
				simpleAuthorizationInfo.addStringPermission(auth.toString());
			}
		}
        */
        return simpleAuthorizationInfo;
    }

}


