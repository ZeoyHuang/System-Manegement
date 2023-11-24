package com.xx.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xx.system.entity.Permission;
import com.xx.system.entity.SysRole;
import com.xx.system.mapper.PermissionMapper;
import com.xx.system.mapper.SysRoleMapper;
import com.xx.system.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    private final SysRoleMapper sysRoleMapper;
    private final PermissionMapper permissionMapper;

    @Override
    public List<String> getDataScopesByRoleIds(List<Long> roleIds) {
        return baseMapper.getDataScopesByRoleIds(roleIds);
    }

    @Override
    public void addRole(SysRole role) {
        save(role);
    }

    @Override
    public void deleteRoleById(Long roleId) {
        removeById(roleId);
    }

    @Override
    public void updateRole(SysRole role) {
        updateById(role);
    }

    @Override
    public List<SysRole> getAllRoles() {
        return list();
    }

    @Override
    public SysRole getRoleById(Long roleId) {
        return getById(roleId);
    }

    @Override
    public void exportRoleToExcel() {
        List<SysRole> roleList = getAllRoles(); // Get the data from your service
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("SysRole Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Role ID", "Name", "Level", "Description", "Data Scope", "Create By", "Update By", "Create Time", "Update Time"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Create data rows
            for (int i = 0; i < roleList.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                SysRole role = roleList.get(i);
                dataRow.createCell(0).setCellValue(role.getRoleId());
                dataRow.createCell(1).setCellValue(role.getName());
                dataRow.createCell(2).setCellValue(role.getLevel());
                dataRow.createCell(3).setCellValue(role.getDescription());
                dataRow.createCell(4).setCellValue(role.getDataScope());
                dataRow.createCell(5).setCellValue(role.getCreateBy());
                dataRow.createCell(6).setCellValue(role.getUpdateBy());
                dataRow.createCell(7).setCellValue(role.getCreateTime().toString()); // You might need to format this based on your needs
                dataRow.createCell(8).setCellValue(role.getUpdateTime().toString()); // You might need to format this based on your needs
            }

            // Write workbook to an Excel file
            try (FileOutputStream outputStream = new FileOutputStream("sys_role_export.xlsx")) {
                workbook.write(outputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // permission assignment
    // get permission
    @Override
    public List<Permission> getPermission(Long roleId) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Permission::getRoleId, roleId);
        return permissionMapper.selectList(queryWrapper);
    }

    // update permission
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean savePermission(Long roleId, Set<Long> menus) {
        // 先根据roleId删除原有权限
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Permission::getRoleId, roleId);
        permissionMapper.delete(queryWrapper);
        // 再插入roleId新权限
        for (Long menu : menus) {
            int rowCount = permissionMapper.insert(
                    new Permission(roleId, menu, Timestamp.valueOf(LocalDateTime.now())));
            if (rowCount <= 0) {
                throw new RuntimeException("保存权限失败");
            }
        }
        return true;
    }
}
