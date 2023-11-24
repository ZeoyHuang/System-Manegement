package com.xx.system.service.impl;

import com.xx.system.entity.SysDict;
import com.xx.system.entity.SysMenu;
import com.xx.system.mapper.SysDictMapper;
import com.xx.system.service.SysDictService;
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
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {
    // Add
    @Override
    public void addDict(SysDict dict) {
        save(dict);
    }

    // Delete
    @Override
    public void deleteDictById(Long dictId) {
        removeById(dictId);
    }

    // Update
    @Override
    public void updateDict(SysDict dict) {
        updateById(dict);
    }

    // Retrieve
    @Override
    public List<SysDict> getAllDicts() {
        return list();
    }
    @Override
    public SysDict getDictById(Long dictId) {
        return getById(dictId);
    }

    // Export
    @Override
    public void exportDictToExcel() {
        List<SysDict> dictList = getAllDicts(); // Get the data from your service
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("SysDict Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Dict ID", "Name", "Description", "Create By", "Update By", "Create Time", "Update Time"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Create data rows
            for (int i = 0; i < dictList.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                SysDict dict = dictList.get(i);
                dataRow.createCell(0).setCellValue(dict.getDictId());
                dataRow.createCell(1).setCellValue(dict.getName());
                dataRow.createCell(2).setCellValue(dict.getDescription());
                dataRow.createCell(3).setCellValue(dict.getCreateBy());
                dataRow.createCell(4).setCellValue(dict.getUpdateBy());
                dataRow.createCell(5).setCellValue(dict.getCreateTime().toString());
                dataRow.createCell(6).setCellValue(dict.getUpdateTime().toString());
            }

            // Write workbook to an Excel file
            try (FileOutputStream outputStream = new FileOutputStream("sys_dict_export.xlsx")) {
                workbook.write(outputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
