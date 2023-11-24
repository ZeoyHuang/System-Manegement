package com.xx.system.service.impl;

import com.xx.system.entity.SysMenu;
import com.xx.system.entity.SysRole;
import com.xx.system.entity.SysUser;
import com.xx.system.mapper.SysUserMapper;
import com.xx.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public List<SysRole> selectSysRoleByUserId(Long userId) {
        return baseMapper.selectSysRoleByUserId(userId);
    }

    @Override
    public List<SysMenu> selectSysMenuByUserId(Long userId) {
        return baseMapper.selectSysMenuByUserId(userId);
    }

    // Add
    @Override
    public void addUser(SysUser user) {
        save(user);
    }

    // Delete
    @Override
    public void deleteUserById(Long userId) {
        removeById(userId);
    }

    // Update
    @Override
    public void updateUser(SysUser user) {
        updateById(user);
    }

    // Retrieve
    @Override
    public List<SysUser> getAllUsers() {
        return list();
    }

    @Override
    public SysUser getUserById(Long userId) {
        return getById(userId);
    }

   // Export to Excel
    @Override
    public void exportUserToExcel() {
        List<SysUser> userList = getAllUsers(); // Get the data from your service
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("SysUser Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"User ID", "Dept ID", "Username", "Nick Name", "Gender", "Phone", "Email", "Avatar Name", "Avatar Path", "Password", "Is Admin", "Enabled", "Create By", "Update By", "Pwd Reset Time", "Create Time", "Update Time"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Create data rows
            for (int i = 0; i < userList.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                SysUser user = userList.get(i);
                dataRow.createCell(0).setCellValue(user.getUserId());
                dataRow.createCell(1).setCellValue(user.getDeptId());
                dataRow.createCell(2).setCellValue(user.getUsername());
                dataRow.createCell(3).setCellValue(user.getNickName());
                dataRow.createCell(4).setCellValue(user.getGender());
                dataRow.createCell(5).setCellValue(user.getPhone());
                dataRow.createCell(6).setCellValue(user.getEmail());
                dataRow.createCell(7).setCellValue(user.getAvatarName());
                dataRow.createCell(8).setCellValue(user.getAvatarPath());
                dataRow.createCell(9).setCellValue(user.getPassword());
                dataRow.createCell(10).setCellValue(user.getIsAdmin());
                dataRow.createCell(11).setCellValue(user.getEnabled());
                dataRow.createCell(12).setCellValue(user.getCreateBy());
                dataRow.createCell(13).setCellValue(user.getUpdateBy());
                dataRow.createCell(14).setCellValue(user.getPwdResetTime().toString()); // You might need to format this based on your needs
                dataRow.createCell(15).setCellValue(user.getCreateTime().toString()); // You might need to format this based on your needs
                dataRow.createCell(16).setCellValue(user.getUpdateTime().toString()); // You might need to format this based on your needs
            }

            // Write workbook to an Excel file
            try (FileOutputStream outputStream = new FileOutputStream("sys_user_export.xlsx")) {
                workbook.write(outputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
