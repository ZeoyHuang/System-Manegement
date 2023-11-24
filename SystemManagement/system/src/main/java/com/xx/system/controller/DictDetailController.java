package com.xx.system.controller;

import com.xx.system.entity.PageSplit;
import com.xx.system.entity.SysDictDetail;
import com.xx.system.mapper.SysDictDetailMapper;
import com.xx.system.mapper.SysDictMapper;
import com.xx.system.service.SysDictDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dict-detail")
public class DictDetailController {
    @Autowired
    private SysDictDetailService sysDictDetailService;

    // Add a new dict detail
    @PostMapping("/add")
    public ResponseEntity<String> addDictDetail(@RequestBody SysDictDetail dictDetail) {
        sysDictDetailService.save(dictDetail);
        return ResponseEntity.ok("Dict detail added successfully");
    }

    // Delete a dict detail by ID
    @DeleteMapping("/delete/{detailId}")
    public ResponseEntity<String> deleteDictDetail(@PathVariable Long detailId) {
        sysDictDetailService.removeById(detailId);
        return ResponseEntity.ok("Dict detail deleted successfully");
    }

    // Update a dict detail
    @PutMapping("/update")
    public ResponseEntity<String> updateDictDetail(@RequestBody SysDictDetail dictDetail) {
        sysDictDetailService.updateById(dictDetail);
        return ResponseEntity.ok("Dict detail updated successfully");
    }

    // Retrieve a dict detail by ID
    @GetMapping("/get/{detailId}")
    public ResponseEntity<SysDictDetail> getDictDetail(@PathVariable Long detailId) {
        SysDictDetail dictDetail = sysDictDetailService.getById(detailId);
        if (dictDetail != null) {
            return ResponseEntity.ok(dictDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Split page for sys_dict
    @Resource
    SysDictDetailMapper sysDictDetailMapper;

    @CrossOrigin(origins ="*",maxAge = 3600)
    @GetMapping("/select")
    //Front-end get request sent in two parameters: currentPage & pageSize
    public PageSplit findSysDictDetail(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize){

        int cuIndex=pageSize==10?(currentPage-1)*pageSize:0*pageSize;
        // Tabular data stored in arrays.
        List list=sysDictDetailMapper.SysDictDetailAll(cuIndex,pageSize);
        // save current page
        int currentPageIndex=currentPage;
        // save page size
        int pageSizeIndex=pageSize;
        // save data
        int totalIndex=sysDictDetailMapper.SysDictDetailIndex();
        PageSplit pageSplit =new PageSplit();
        pageSplit.setList(list);
        pageSplit.setCurrentPage(currentPageIndex);
        pageSplit.setPageSize(pageSizeIndex);
        pageSplit.setTotal(totalIndex);

        return pageSplit;
    }
}
