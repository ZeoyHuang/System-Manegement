package com.xx.logging.controller;

import com.xx.logging.entity.SysLog;
import com.xx.logging.service.SysLogService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/logs")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private SysLogService sysLogService;

    // querying by username
    @GetMapping("/byUser/{username}")
    public List<SysLog> getLogsByUser(@PathVariable String username) {
        return sysLogService.getLogsByUser(username);
    }

    // querying by date range
    @GetMapping("/byDateRange")
    public List<SysLog> getLogsByDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return sysLogService.getLogsByDateRange(startDate, endDate);
    }

    // export to excel
    @GetMapping("/exportExcel")
    public ResponseEntity<Resource> exportLogsToExcel() {
        List<SysLog> logs = sysLogService.list();
        Workbook workbook = createExcelWorkbook(logs);

        ByteArrayResource resource = excelWorkbookToByteArrayResource(workbook);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=logs.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body((Resource) resource);
    }

    private Workbook createExcelWorkbook(List<SysLog> logs) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Logs");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Log ID", "Description", "Log Type", "Method", "Params", "Request IP",
                "Time", "Username", "Address", "Browser", "Exception Detail", "Create Time"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Fill data rows
        for (int i = 0; i < logs.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            SysLog log = logs.get(i);

            // Fill cells with log data
            dataRow.createCell(0).setCellValue(log.getLogId());
            dataRow.createCell(1).setCellValue(log.getDescription());
            dataRow.createCell(2).setCellValue(log.getLogType());
            dataRow.createCell(3).setCellValue(log.getMethod());
            dataRow.createCell(4).setCellValue(log.getParams());
            dataRow.createCell(5).setCellValue(log.getRequestIp());
            dataRow.createCell(6).setCellValue(log.getTime());
            dataRow.createCell(7).setCellValue(log.getUsername());
            dataRow.createCell(8).setCellValue(log.getAddress());
            dataRow.createCell(9).setCellValue(log.getBrowser());
            dataRow.createCell(10).setCellValue(log.getExceptionDetail());
            dataRow.createCell(11).setCellValue(log.getCreateTime().toString());
        }

        return workbook;
    }

    private ByteArrayResource excelWorkbookToByteArrayResource(Workbook workbook) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            byte[] excelBytes = outputStream.toByteArray();
            return new ByteArrayResource(excelBytes);
        } catch (Exception e) {
            logger.error("Error while converting workbook to byte array resource: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create Excel export.", e);
        }
    }

    // querying exception logs
    @GetMapping("/exceptionLogs")
    public List<SysLog> getExceptionLogs() {
        return sysLogService.getExceptionLogs();
    }

    // viewing the details of the exception log
    @GetMapping("/exceptionLog/{logId}")
    public ResponseEntity<SysLog> getExceptionLogDetails(@PathVariable Long logId) {
        SysLog exceptionLog = sysLogService.getExceptionLogById(logId);
        return ResponseEntity.ok(exceptionLog);
    }

    // exporting exception logs to Excel
    @GetMapping("/exportExceptionExcel")
    public ResponseEntity<Resource> exportExceptionLogsToExcel() {
        List<SysLog> exceptionLogs = sysLogService.getExceptionLogs();
        Workbook workbook = createExcelWorkbook(exceptionLogs);

        ByteArrayResource resource = excelWorkbookToByteArrayResource(workbook);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=exception-logs.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body((Resource) resource);
    }
}
