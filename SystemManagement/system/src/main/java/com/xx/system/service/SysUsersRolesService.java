package com.xx.system.service;

import com.xx.system.entity.SysUsersRoles;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */
public interface SysUsersRolesService extends IService<SysUsersRoles> {
    List<Long> getRoleIdsByUserId(Long userId);
}
