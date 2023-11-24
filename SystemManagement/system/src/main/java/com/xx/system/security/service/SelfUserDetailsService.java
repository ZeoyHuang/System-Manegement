package com.xx.system.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xx.system.entity.SysUser;
import com.xx.system.security.entity.SelfUserEntity;
import com.xx.system.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * SpringSecurity用户的业务实现
 * @Author Sans
 * @CreateTime 2019/10/1 17:21
 */
@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询用户信息
     * @Author Sans
     * @CreateTime 2019/9/13 17:23
     * @Param  username  用户名
     * @Return UserDetails SpringSecurity用户信息
     */
    @Override
    public SelfUserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername,username));
        if (sysUser!=null){
            SelfUserEntity selfUserEntity = new SelfUserEntity();
            BeanUtils.copyProperties(sysUser,selfUserEntity);
            return selfUserEntity;
        }
        return null;
    }
}