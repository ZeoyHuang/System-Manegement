package com.xx.system.controller;

import com.xx.system.entity.SysMenu;
import com.xx.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private SysMenuService sysMenuService;

    // Add a new menu
    @PostMapping("/add")
    public ResponseEntity<String> addMenu(@RequestBody SysMenu menu) {
        sysMenuService.save(menu);
        return ResponseEntity.ok("Menu added successfully");
    }

    // Delete a menu by ID
    @DeleteMapping("/delete/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable Long menuId) {
        sysMenuService.removeById(menuId);
        return ResponseEntity.ok("Menu deleted successfully");
    }

    // Update a menu
    @PutMapping("/update")
    public ResponseEntity<String> updateMenu(@RequestBody SysMenu menu) {
        sysMenuService.updateById(menu);
        return ResponseEntity.ok("Menu updated successfully");
    }

    // Retrieve a menu by ID
    @GetMapping("/get/{menuId}")
    public ResponseEntity<SysMenu> getMenu(@PathVariable Long menuId) {
        SysMenu menu = sysMenuService.getById(menuId);
        if (menu != null) {
            return ResponseEntity.ok(menu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all submenu IDs for a given menu ID
    @GetMapping("/submenus/{menuId}")
    public ResponseEntity<List<Long>> getSubmenuIds(@PathVariable Long menuId) {
        List<Long> submenuIds = sysMenuService.getAllSubmenuIdsByMenuIds(menuId);
        return ResponseEntity.ok(submenuIds);
    }

    // Get siblings and superiors data for a given menu ID
    @GetMapping("/siblings-superiors/{menuId}")
    public ResponseEntity<List<SysMenu>> getSiblingsAndSuperiors(@PathVariable Long menuId) {
        List<SysMenu> siblingsAndSuperiors = sysMenuService.getSuperiorsAndSiblings(menuId);
        return ResponseEntity.ok(siblingsAndSuperiors);
    }

    // Get the menu according to the current user permissions
    @GetMapping("/getMenuListByDataScope")
    public ResponseEntity<List<SysMenu>> getMenuListByDataScope(
            @RequestParam Long userId,
            @RequestParam Long menuId
    ) {
        List<SysMenu> menuList = sysMenuService.getMenuListByDataScope(userId, menuId);
        return ResponseEntity.ok(menuList);
    }
}
