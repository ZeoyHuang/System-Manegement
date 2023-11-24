package com.xx.system.mapper;

import com.xx.system.entity.SysMenu;
import com.xx.system.entity.SysRole;
import com.xx.system.entity.SysUser;
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
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("SELECT * FROM sys_role r WHERE EXISTS(SELECT 1 FROM sys_users_roles ur WHERE r.role_id = ur.role_id AND ur.user_id = #{userId})")
    List<SysRole> selectSysRoleByUserId(@Param("userId") Long userId);

    @Select("SELECT * FROM sys_role r WHERE EXISTS(SELECT 1 FROM sys_users_roles ur WHERE r.role_id = ur.role_id AND ur.user_id = #{userId})")
    List<SysMenu> selectSysMenuByUserId(@Param("userId") Long userId);

    // Search database for paginated user data
    @Select("SELECT * FROM sys_user LIMIT #{currentPage},#{pageSize}")
    List<SysUser> SysUserAll(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);

    // Get total count of user data in the database
    @Select("SELECT COUNT(*) FROM sys_user")
    int SysUserIndex();
}
