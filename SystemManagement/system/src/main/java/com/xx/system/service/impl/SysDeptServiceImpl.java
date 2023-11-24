package com.xx.system.service.impl;

import com.xx.system.entity.SysDept;
import com.xx.system.entity.SysMenu;
import com.xx.system.mapper.SysDeptMapper;
import com.xx.system.service.SysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    // Add
    @Override
    public void addDept(SysDept dept) {
        save(dept);
    }

    // Delete
    @Override
    public void deleteDeptById(Long deptId) {
        removeById(deptId);
    }

    // Update
    @Override
    public void updateDept(SysDept dept) {
        updateById(dept);
    }

    // Retrieve
    @Override
    public List<SysDept> getAllDepts() {
        return list();
    }
    @Override
    public SysDept getDeptById(Long deptId) {
        return getById(deptId);
    }

    // Export

    @Override
    public void exportDeptToExcel() {
        List<SysDept> deptList = getAllDepts(); // Get the data from your service
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("SysDept Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Dept ID", "Parent ID", "Sub Count", "Name", "Dept Sort", "Enabled", "Create By", "Update By", "Create Time", "Update Time"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Create data rows
            for (int i = 0; i < deptList.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                SysDept dept = deptList.get(i);
                dataRow.createCell(0).setCellValue(dept.getDeptId());
                dataRow.createCell(1).setCellValue(dept.getPid());
                dataRow.createCell(2).setCellValue(dept.getSubCount());
                dataRow.createCell(3).setCellValue(dept.getName());
                dataRow.createCell(4).setCellValue(dept.getDeptSort());
                dataRow.createCell(5).setCellValue(dept.getEnabled());
                dataRow.createCell(6).setCellValue(dept.getCreateBy());
                dataRow.createCell(7).setCellValue(dept.getUpdateBy());
                dataRow.createCell(8).setCellValue(dept.getCreateTime().toString()); // You might need to format this based on your needs
                dataRow.createCell(9).setCellValue(dept.getUpdateTime().toString()); // You might need to format this based on your needs
            }

            // Write workbook to an Excel file
            try (FileOutputStream outputStream = new FileOutputStream("sys_dept_export.xlsx")) {
                workbook.write(outputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
