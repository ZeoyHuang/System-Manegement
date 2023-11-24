package com.xx.system.service;

import com.xx.system.entity.Permission;
import com.xx.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */
public interface SysRoleService extends IService<SysRole> {
    List<String> getDataScopesByRoleIds(List<Long> roleIds);

    void addRole(SysRole role);
    void deleteRoleById(Long roleId);
    void updateRole(SysRole role);
    List<SysRole> getAllRoles();
    SysRole getRoleById(Long roleId);

    // Export the sys_role to excel
    void exportRoleToExcel();

    // permission assignment
    /**
     * 获取角色权限
     *
     * @param roleId 角色id
     * @return 角色权限列表
     */
    List<Permission> getPermission(Long roleId);

    /**
     * 保存角色权限
     *
     * @param roleId 角色id
     * @param menus 权限表
     * @return 是否成功
     */
    Boolean savePermission(Long roleId, Set<Long> menus);
}
