package com.xx.system.security.config;

import com.xx.common.exception.ServiceException;
import com.xx.system.security.util.ResultUtil;
import com.xx.tools.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // authentication/form是认证时的请求接口，验证码校验只需要匹配这个接口即可
        if ("/api/login".equals(request.getRequestURI()) &&
                "POST".equals(request.getMethod())) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ServiceException e) {

                // 封装返回参数
                Map<String,Object> resultData = new HashMap<>();
                resultData.put("code",e.getCode());
                resultData.put("msg", e.getMessage());
                ResultUtil.responseJson(response,resultData);
                return;
            }
        }
        // 无异常即校验成功，放行。
        filterChain.doFilter(request, response);

    }

    private void validate(ServletWebRequest request) throws ServiceException {
        // 从客户端接收到的验证码
        String verifyCode = request.getParameter("verifyCode");

        boolean exists = redisUtils.exists(verifyCode);
        if(StringUtils.isNotBlank(verifyCode) && exists){

            // 校验成功之后，移除验证码
            redisUtils.remove(verifyCode);
            return;
        }
        throw new ServiceException("500", "验证码不正确或者已经过期");

    }
}