package com.xx.system.mapper;

import com.xx.system.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<String> getDataScopesByRoleIds(@Param("roleIds") List<Long> roleIds);

    // Search database for paginated role data
    @Select("SELECT * FROM sys_role LIMIT #{currentPage},#{pageSize}")
    List<SysRole> SysRoleAll(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);

    // Get total count of role data in the database
    @Select("SELECT COUNT(*) FROM sys_role")
    int SysRoleIndex();
}
