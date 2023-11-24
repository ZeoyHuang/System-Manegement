package com.xx.system.mapper;

import com.xx.system.entity.SysUsersRoles;
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
public interface SysUsersRolesMapper extends BaseMapper<SysUsersRoles> {
    List<Long> getRoleIdsByUserId(@Param("userId") Long userId);
}
