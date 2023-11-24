package com.xx.system.controller;

import com.xx.system.entity.PageSplit;
import com.xx.system.entity.SysDict;
import com.xx.system.mapper.SysDictMapper;
import com.xx.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController {
    @Autowired
    private SysDictService sysDictService;

    // Add a new dict
    @PostMapping("/add")
    public ResponseEntity<String> addDict(@RequestBody SysDict dict) {
        sysDictService.save(dict);
        return ResponseEntity.ok("Dict added successfully");
    }

    // Delete a dict by ID
    @DeleteMapping("/delete/{dictId}")
    public ResponseEntity<String> deleteDict(@PathVariable Long dictId) {
        sysDictService.removeById(dictId);
        return ResponseEntity.ok("Dict deleted successfully");
    }

    // Update a dict
    @PutMapping("/update")
    public ResponseEntity<String> updateDict(@RequestBody SysDict dict) {
        sysDictService.updateById(dict);
        return ResponseEntity.ok("Dict updated successfully");
    }

    // Retrieve a dict by ID
    @GetMapping("/get/{dictId}")
    public ResponseEntity<SysDict> getDict(@PathVariable Long dictId) {
        SysDict dict = sysDictService.getById(dictId);
        if (dict != null) {
            return ResponseEntity.ok(dict);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Split page for sys_dict
    @Resource
    SysDictMapper sysDictMapper;

    @CrossOrigin(origins ="*",maxAge = 3600)
    @GetMapping("/select")
    //Front-end get request sent in two parameters: currentPage & pageSize
    public PageSplit findSysDict(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize){

        int cuIndex=pageSize==10?(currentPage-1)*pageSize:0*pageSize;
        // Tabular data stored in arrays.
        List list=sysDictMapper.SysDictAll(cuIndex,pageSize);
        // save current page
        int currentPageIndex=currentPage;
        // save page size
        int pageSizeIndex=pageSize;
        // save data
        int totalIndex=sysDictMapper.SysDictIndex();
        PageSplit pageSplit =new PageSplit();
        pageSplit.setList(list);
        pageSplit.setCurrentPage(currentPageIndex);
        pageSplit.setPageSize(pageSizeIndex);
        pageSplit.setTotal(totalIndex);

        return pageSplit;
    }

    // export sys_dict to Excel file
    @PostMapping("/export")
    public ResponseEntity<String> exportDictToExcel() {
        sysDictService.exportDictToExcel();
        return ResponseEntity.ok("Export complete");
    }
}
