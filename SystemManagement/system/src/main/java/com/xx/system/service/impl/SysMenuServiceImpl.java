package com.xx.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xx.system.entity.SysMenu;
import com.xx.system.mapper.SysMenuMapper;
import com.xx.system.service.SysMenuService;
import com.xx.system.service.SysRoleService;
import com.xx.system.service.SysUsersRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysUsersRolesService sysUsersRolesService;
    @Autowired
    private SysRoleService sysRoleService;

    // Add
    @Override
    public void addMenu(SysMenu menu) {
        save(menu);
    }

    // Delete
    @Override
    public void deleteMenuById(Long menuId) {
        removeById(menuId);
    }

    // Update
    @Override
    public void updateMenu(SysMenu menu) {
        updateById(menu);
    }

    // Retrieve
    @Override
    public List<SysMenu> getAllMenus() {
        return list();
    }
    @Override
    public SysMenu getMenuById(Long menuId) {
        return getById(menuId);
    }

    // Get all submenu ID
    @Override
    public List<Long> getAllSubmenuIdsByMenuIds(Long menuId) {
        List<Long> submenuIds = new ArrayList<>();
        collectSubmenuIds(menuId, submenuIds);
        return submenuIds;
    }

    private void collectSubmenuIds(Long menuId, List<Long> submenuIds) {
        // Fetch submenus of the current menu
        List<SysMenu> submenus = list(new QueryWrapper<SysMenu>().eq("pid", menuId));
        for (SysMenu submenu : submenus) {
            submenuIds.add(submenu.getMenuId()); // Add the submenu ID
            // Recursively fetch submenus of this submenu
            collectSubmenuIds(submenu.getMenuId(), submenuIds);
        }
    }

    // Get superiors and siblings
    @Override
    public List<SysMenu> getSuperiorsAndSiblings(Long menuId) {
        List<SysMenu> superiorsAndSiblings = new ArrayList<>();

        // Fetch the menu with the provided menuId
        SysMenu currentMenu = getById(menuId);

        if (currentMenu != null) {
            // Fetch parent (superior) menu
            Long parentId = currentMenu.getPid();
            if (parentId != null) {
                SysMenu parentMenu = getById(parentId);
                if (parentMenu != null) {
                    superiorsAndSiblings.add(parentMenu); // Add the parent menu
                }
            }

            // Fetch sibling menus
            Long currentMenuPid = currentMenu.getPid();
            if (currentMenuPid != null) {
                List<SysMenu> siblingMenus = list(new QueryWrapper<SysMenu>()
                        .eq("pid", currentMenuPid)
                        .ne("menu_id", menuId));
                superiorsAndSiblings.addAll(siblingMenus);
            }
        }

        return superiorsAndSiblings;
    }

    //
    @Override
    public List<SysMenu> getMenuListByDataScope(Long userId, Long menuId) {
        List<Long> roleIds = sysUsersRolesService.getRoleIdsByUserId(userId);
        List<String> dataScopes = sysRoleService.getDataScopesByRoleIds(roleIds);

        if (dataScopes.contains("全部")) {
            // If the data scope is '全部', return all sys_menu
            return list();
        } else if (dataScopes.contains("本级")) {
            // If the data scope is '本级', return sibling menus
            List<Long> siblingMenuIds = baseMapper.getSiblingMenuIdsByUserIdAndMenuId(userId, menuId);
            if (siblingMenuIds.isEmpty()) {
                return new ArrayList<>(); // No siblings found, return an empty list
            } else {
                return listByIds(siblingMenuIds);
            }
        } else {
            return new ArrayList<>(); // Return an empty list as default
        }
    }
}
