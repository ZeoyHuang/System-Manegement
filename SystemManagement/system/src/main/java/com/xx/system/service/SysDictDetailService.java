package com.xx.system.service;

import com.xx.system.entity.SysDictDetail;
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
public interface SysDictDetailService extends IService<SysDictDetail> {
    void addDictDetail(SysDictDetail detail);
    void deleteDictDetailById(Long detailId);
    void updateDictDetail(SysDictDetail detail);
    List<SysDictDetail> getAllDictDetails();
    SysDictDetail getDictDetailById(Long detailId);
}
