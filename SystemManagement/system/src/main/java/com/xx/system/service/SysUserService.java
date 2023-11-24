package com.xx.system.service;

import com.xx.system.entity.SysMenu;
import com.xx.system.entity.SysRole;
import com.xx.system.entity.SysUser;
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
public interface SysUserService extends IService<SysUser> {

    List<SysRole> selectSysRoleByUserId(Long userId);

    List<SysMenu> selectSysMenuByUserId(Long userId);

    void addUser(SysUser user);
    void deleteUserById(Long userId);
    void updateUser(SysUser user);
    List<SysUser> getAllUsers();
    SysUser getUserById(Long userId);

    // export the user to excel
    void exportUserToExcel();
}
