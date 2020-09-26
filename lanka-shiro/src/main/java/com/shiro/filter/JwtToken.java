package com.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author Nxy
 * @title: JwtToken
 * @projectName lanka
 * @description: TODO
 */
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    /**
     * Token
     */
    private String token;

    public JwtToken(String token) {
        super();
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}