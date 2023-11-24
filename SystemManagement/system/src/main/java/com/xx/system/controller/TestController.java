package com.xx.system.controller;

import com.xx.logging.annotation.OpLog;
import com.xx.tools.utils.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
public class TestController {

    @OpLog(opType= "ADD",opDesc = "添加测试")
    @GetMapping("/test1")
    public R test1(String msg)  {
        return R.ok();
    }

    @OpLog(opType= "ADD",opDesc = "添加测试")
    @GetMapping("/test2")
    public R test2(String msg)  {
        System.out.println(msg);
        System.out.println(1/0);
        return R.ok();
    }

    @PreAuthorize("hasRole('ROLE_超级管理员')")
    @GetMapping("test3")
    public R userList() {
        return R.ok();
    }
}
