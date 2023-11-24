package com.xx.system.security.handler;

import com.xx.system.security.config.JWTConfig;
import com.xx.system.security.entity.SelfUserEntity;
import com.xx.system.security.util.ResultUtil;
import com.xx.tools.utils.RedisUtils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登出成功处理类
 * @Author Sans
 * @CreateTime 2019/10/3 9:42
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 用户登出返回结果
     * 这里应该让前端清除掉Token
     * @Author Sans
     * @CreateTime 2019/10/3 9:50
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code","200");
        resultData.put("msg", "登出成功");
        fromRedisClearToken(request);
        SecurityContextHolder.clearContext();
        ResultUtil.responseJson(response,ResultUtil.resultSuccess(resultData));
    }

    /**
     * 通过用户名删除 token
     * @param
     */
    private void fromRedisClearToken(HttpServletRequest httpServletRequest) {

        // 获取请求头中JWT的Token
        String tokenHeader = httpServletRequest.getHeader(JWTConfig.tokenHeader);
        if (null!=tokenHeader && tokenHeader.startsWith(JWTConfig.tokenPrefix)) {
            try {
                // 截取JWT前缀
                String token = tokenHeader.replace(JWTConfig.tokenPrefix, "");
                // 解析JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(JWTConfig.secret)
                        .parseClaimsJws(token)
                        .getBody();
                // 获取用户名
                String username = claims.getSubject();
                redisUtils.remove(username);


            } catch (ExpiredJwtException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedJwtException e) {
                throw new RuntimeException(e);
            } catch (MalformedJwtException e) {
                throw new RuntimeException(e);
            } catch (SignatureException e) {
                throw new RuntimeException(e);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }
    }
}