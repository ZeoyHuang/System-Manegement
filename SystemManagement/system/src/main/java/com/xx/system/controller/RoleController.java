package com.xx.system.controller;

import com.xx.system.entity.PageSplit;
import com.xx.system.entity.SysRole;
import com.xx.system.mapper.SysRoleMapper;
import com.xx.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Api(tags = "角色信息接口")
public class RoleController {
    @Autowired
    private SysRoleService sysRoleService;

    // Add a new role
    @PostMapping("/add")
    public ResponseEntity<String> addRole(@RequestBody SysRole role) {
        sysRoleService.save(role);
        return ResponseEntity.ok("Role added successfully");
    }

    // Delete a role by ID
    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable Long roleId) {
        sysRoleService.removeById(roleId);
        return ResponseEntity.ok("Role deleted successfully");
    }

    // Update a role
    @PutMapping("/update")
    public ResponseEntity<String> updateRole(@RequestBody SysRole role) {
        sysRoleService.updateById(role);
        return ResponseEntity.ok("Role updated successfully");
    }

    // Retrieve a role by ID
    @GetMapping("/get/{roleId}")
    public ResponseEntity<SysRole> getRole(@PathVariable Long roleId) {
        SysRole role = sysRoleService.getById(roleId);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Split page for sys_role
    @Resource
    SysRoleMapper sysRoleMapper;

    @CrossOrigin(origins ="*",maxAge = 3600)
    @GetMapping("/select")
    //Front-end get request sent in two parameters: currentPage & pageSize
    public PageSplit findSysRole(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize){

        int cuIndex=pageSize==10?(currentPage-1)*pageSize:0*pageSize;
        // Tabular data stored in arrays.
        List list=sysRoleMapper.SysRoleAll(cuIndex,pageSize);
        // save current page
        int currentPageIndex=currentPage;
        // save page size
        int pageSizeIndex=pageSize;
        // save data
        int totalIndex=sysRoleMapper.SysRoleIndex();
        PageSplit pageSplit =new PageSplit();
        pageSplit.setList(list);
        pageSplit.setCurrentPage(currentPageIndex);
        pageSplit.setPageSize(pageSizeIndex);
        pageSplit.setTotal(totalIndex);

        return pageSplit;
    }

    // export sys_role to Excel file
    @PostMapping("/export")
    public ResponseEntity<String> exportRoleToExcel() {
        sysRoleService.exportRoleToExcel();
        return ResponseEntity.ok("Export complete");
    }

    // permission assignment
    @ApiOperation("获取角色权限信息")
    @GetMapping("{roleId}/permission")
    public ResponseEntity<Object> getPermission(@PathVariable Long roleId) {
        return ResponseEntity.ok(sysRoleService.getPermission(roleId));
    }

    @ApiOperation("保存角色权限信息")
    @PostMapping("{roleId}/permission")
    public ResponseEntity<Object> savePermission(@PathVariable Long roleId,@RequestBody Set<Long> menus) {
        return ResponseEntity.ok(sysRoleService.savePermission(roleId,menus));
    }
}
