package com.xx.system.controller;

import com.xx.system.entity.PageSplit;
import com.xx.system.entity.SysUser;
import com.xx.system.mapper.SysUserMapper;
import com.xx.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    // Add a new user
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody SysUser user) {
        sysUserService.save(user);
        return ResponseEntity.ok("User added successfully");
    }

    // Delete a user by ID
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        sysUserService.removeById(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    // Update a user
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody SysUser user) {
        sysUserService.updateById(user);
        return ResponseEntity.ok("User updated successfully");
    }

    // Retrieve a user by ID
    @GetMapping("/get/{userId}")
    public ResponseEntity<SysUser> getUser(@PathVariable Long userId) {
        SysUser user = sysUserService.getById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Split page for sys_user
    @Resource
    SysUserMapper sysUserMapper;

    @CrossOrigin(origins ="*",maxAge = 3600)
    @GetMapping("/select")
    //Front-end get request sent in two parameters: currentPage & pageSize
    public PageSplit findSysUser(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize){

        int cuIndex=pageSize==10?(currentPage-1)*pageSize:0*pageSize;
        // Tabular data stored in arrays.
        List list=sysUserMapper.SysUserAll(cuIndex,pageSize);
        // save current page
        int currentPageIndex=currentPage;
        // save page size
        int pageSizeIndex=pageSize;
        // save data
        int totalIndex=sysUserMapper.SysUserIndex();
        PageSplit pageSplit =new PageSplit();
        pageSplit.setList(list);
        pageSplit.setCurrentPage(currentPageIndex);
        pageSplit.setPageSize(pageSizeIndex);
        pageSplit.setTotal(totalIndex);

        return pageSplit;
    }

    // export sys_user to Excel file
    @PostMapping("/export")
    public ResponseEntity<String> exportUserToExcel() {
        sysUserService.exportUserToExcel();
        return ResponseEntity.ok("Export complete");
    }
}
