package com.xx.system.security.handler;

import com.xx.system.security.config.JWTConfig;
import com.xx.system.security.entity.SelfUserEntity;
import com.xx.system.security.util.JWTTokenUtil;
import com.xx.system.security.util.ResultUtil;
import com.xx.tools.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 登录成功处理类
 * @Author Sans
 * @CreateTime 2019/10/3 9:13
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 登录成功返回结果
     * @Author Sans
     * @CreateTime 2019/10/3 9:27
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        // 组装JWT
        SelfUserEntity selfUserEntity =  (SelfUserEntity) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(selfUserEntity);
        setTokenToRedis(selfUserEntity.getUsername(), token);
        token = JWTConfig.tokenPrefix + token;
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code","200");
        resultData.put("msg", "登录成功");
        resultData.put("token",token);
        resultData.put("userInfo", selfUserEntity);
        ResultUtil.responseJson(response,resultData);
    }

    /**
     * 设置token到 redis 中
     * @param token
     */
    private void setTokenToRedis(String username, String token) {
        redisUtils.setWithTime(username, token, JWTConfig.expiration);
    }
}