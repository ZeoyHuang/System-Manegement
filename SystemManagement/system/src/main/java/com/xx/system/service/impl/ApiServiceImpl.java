package com.xx.system.service.impl;

import com.xx.common.exception.ServiceException;
import com.xx.system.security.entity.SelfUserEntity;
import com.xx.system.security.util.SecurityUtil;
import com.xx.system.service.ApiService;
import com.xx.tools.utils.RedisUtils;
import com.xx.tools.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/***
 * @title ApiServiceImpl
 * @description <description class purpose>
 * @author WeiShuo
 * @version 1.0.0
 * @create 2023/8/14 21:53
 **/
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 获取验证码：4位 随机字母 + 数字
     * @return
     */
    @Override
    public String getVerifyCode() {


        String code = generateWord();
        redisUtils.setWithTime(code, code, 60);
        return code;
    }

    public static void main(String[] args) {
        String s = generateWord();
        System.out.println("s = " + s);

    }

    private static String generateWord() {
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        return result;
    }

    /**
     * 获取用户信息
     * @return
     */
    @Override
    public SelfUserEntity getUserInfo() {
        return SecurityUtil.getUserInfo();
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public void exist() {

    }
}
