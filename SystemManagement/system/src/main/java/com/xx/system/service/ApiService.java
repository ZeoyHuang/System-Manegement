package com.xx.system.service;

import com.xx.system.security.entity.SelfUserEntity;

/***
 * @title ApiService
 * @description <description class purpose>
 * @author WeiShuo
 * @version 1.0.0
 * @create 2023/8/14 21:40
 **/
public interface ApiService {

    /**
     * 获取验证码：4位 随机字母 + 数字
     * @return
     */
    String getVerifyCode();

    /**
     * 获取用户信息
     * @return
     */
    SelfUserEntity getUserInfo();

    /**
     * 退出登录
     * @return
     */
    void exist();
}
