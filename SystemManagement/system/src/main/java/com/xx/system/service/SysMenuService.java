package com.xx.system.service;

import com.xx.system.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.system.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */
public interface SysMenuService extends IService<SysMenu> {
    void addMenu(SysMenu menu);
    void deleteMenuById(Long menuId);
    void updateMenu(SysMenu menu);
    List<SysMenu> getAllMenus();
    SysMenu getMenuById(Long menuId);
    List<Long> getAllSubmenuIdsByMenuIds(Long menuIds);
    List<SysMenu> getSuperiorsAndSiblings(Long menuId);
    List<SysMenu> getMenuListByDataScope(Long userId, Long menuId);
}
