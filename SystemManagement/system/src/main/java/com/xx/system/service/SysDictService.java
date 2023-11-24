package com.xx.system.service;

import com.xx.system.entity.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xx.system.entity.SysMenu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */
public interface SysDictService extends IService<SysDict> {
    void addDict(SysDict dict);
    void deleteDictById(Long dictId);
    void updateDict(SysDict dict);
    List<SysDict> getAllDicts();
    SysDict getDictById(Long dictId);
    void exportDictToExcel();
}
