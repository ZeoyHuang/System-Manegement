package com.xx.system.controller;

import com.xx.system.entity.SysDept;
import com.xx.system.entity.PageSplit;
import com.xx.system.mapper.SysDeptMapper;
import com.xx.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private SysDeptService sysDeptService;

    // Add a new dept
    @PostMapping("/add")
    public ResponseEntity<String> addDept(@RequestBody SysDept dept) {
        sysDeptService.save(dept);
        return ResponseEntity.ok("Dept added successfully");
    }

    // Delete a dept by ID
    @DeleteMapping("/delete/{deptId}")
    public ResponseEntity<String> deleteDept(@PathVariable Long deptId) {
        sysDeptService.removeById(deptId);
        return ResponseEntity.ok("Dept deleted successfully");
    }

    // Update a dept
    @PutMapping("/update")
    public ResponseEntity<String> updateDept(@RequestBody SysDept dept) {
        sysDeptService.updateById(dept);
        return ResponseEntity.ok("Dept updated successfully");
    }

    // Retrieve a dept by ID
    @GetMapping("/get/{deptId}")
    public ResponseEntity<SysDept> getDept(@PathVariable Long deptId) {
        SysDept dept = sysDeptService.getById(deptId);
        if (dept != null) {
            return ResponseEntity.ok(dept);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Split page for sys_dept
    @Resource
    SysDeptMapper sysDeptMapper;

    @CrossOrigin(origins ="*",maxAge = 3600)
    @GetMapping("/select")
    //Front-end get request sent in two parameters: currentPage & pageSize
    public PageSplit findSysDept(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize){

        int cuIndex=pageSize==10?(currentPage-1)*pageSize:0*pageSize;
        // Tabular data stored in arrays.
        List list=sysDeptMapper.SysDeptAll(cuIndex,pageSize);
        // save current page
        int currentPageIndex=currentPage;
        // save page size
        int pageSizeIndex=pageSize;
        // save data
        int totalIndex=sysDeptMapper.SysDeptIndex();
        PageSplit pageSplit =new PageSplit();
        pageSplit.setList(list);
        pageSplit.setCurrentPage(currentPageIndex);
        pageSplit.setPageSize(pageSizeIndex);
        pageSplit.setTotal(totalIndex);

        return pageSplit;
    }

    // export sys_dept to Excel file
    @PostMapping("/export")
    public ResponseEntity<String> exportDeptToExcel() {
        sysDeptService.exportDeptToExcel();
        return ResponseEntity.ok("Export complete");
    }
}
