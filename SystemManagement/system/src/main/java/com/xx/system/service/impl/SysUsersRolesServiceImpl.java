package com.xx.system.service.impl;

import com.xx.system.entity.SysUsersRoles;
import com.xx.system.mapper.SysUsersRolesMapper;
import com.xx.system.service.SysUsersRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */
@Service
public class SysUsersRolesServiceImpl extends ServiceImpl<SysUsersRolesMapper, SysUsersRoles> implements SysUsersRolesService {
    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        return baseMapper.getRoleIdsByUserId(userId);
    }
}
