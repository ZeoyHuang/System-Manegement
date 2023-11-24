package com.xx.system.controller;

import com.xx.logging.annotation.OpLog;
import com.xx.system.security.entity.SelfUserEntity;
import com.xx.system.service.ApiService;
import com.xx.tools.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lotey
 * @version 1.0
 * @date 2023/8/12 22:55
 * @desc 功能描述
 */
@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    /**
     * 获取验证码：4位 随机字母 + 数字
     * @param
     * @return
     */
    @GetMapping("/getVerifyCode")
    public R getVerifyCode()  {

        String code = apiService.getVerifyCode();
        return R.ok("获取成功",code);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/getUserInfo")
    public R getUserInfo() {

        SelfUserEntity selfUserEntity = apiService.getUserInfo();
        return R.ok("获取成功", selfUserEntity);
    }
}
