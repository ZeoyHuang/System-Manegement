package com.xx.system.service;

import com.xx.system.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.system.entity.SysMenu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */
public interface SysDeptService extends IService<SysDept> {
    void addDept(SysDept dept);
    void deleteDeptById(Long deptId);
    void updateDept(SysDept dept);
    List<SysDept> getAllDepts();
    SysDept getDeptById(Long deptId);

    // export the sys_dept to excel
    void exportDeptToExcel();
}
