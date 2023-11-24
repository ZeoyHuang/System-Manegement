package com.xx.system.mapper;

import com.xx.system.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<Long> getSubmenuIdsByMenuId(Long menuId);
    List<SysMenu> getSuperiorsAndSiblings(Long menuId);
    List<Long> getSiblingMenuIdsByUserIdAndMenuId(@Param("userId") Long userId, @Param("menuId") Long menuId);
}
