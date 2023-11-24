package com.xx.system.mapper;

import com.xx.system.entity.SysDict;
import com.xx.system.entity.SysDictDetail;
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
public interface SysDictDetailMapper extends BaseMapper<SysDictDetail> {
    //搜索数据库分页对应数据
    @Select("select * from sys_dict limit #{currentPage},#{pageSize}")
    List<SysDict> SysDictDetailAll(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);

    //搜索数据库数据总条数
    @Select("SELECT count(*) FROM sys_dict")
    int SysDictDetailIndex();

}
