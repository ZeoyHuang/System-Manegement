package com.xx.system.security;

import com.xx.system.entity.SysRole;
import com.xx.system.security.config.RsaPasswordEncoder;
import com.xx.system.security.entity.SelfUserEntity;
import com.xx.system.security.service.SelfUserDetailsService;
import com.xx.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义登录验证
 * @Author Sans
 * @CreateTime 2019/10/1 19:11
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SelfUserDetailsService selfUserDetailsService;
    @Autowired
    private SysUserService sysUserService;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RsaPasswordEncoder rsaPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单输入中返回的用户名
        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();

        // 查询用户是否存在
        SelfUserEntity userInfo = selfUserDetailsService.loadUserByUsername(userName);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
//        if (!bCryptPasswordEncoder.matches(password, userInfo.getPassword())) {
//            throw new BadCredentialsException("密码不正确");
//        }
        if (!rsaPasswordEncoder.matches(password, userInfo.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
//        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
//        if (userInfo.getStatus().equals("PROHIBIT")){
//            throw new LockedException("该用户已被冻结");
//        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<SysRole> sysRoleList = sysUserService.selectSysRoleByUserId(userInfo.getUserId());
        for (SysRole sysRoleEntity: sysRoleList){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getName()));
        }
        userInfo.setAuthorities(authorities);
        // 进行登录
        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}