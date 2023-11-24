package com.xx.system.service.impl;

import com.xx.system.entity.SysDictDetail;
import com.xx.system.entity.SysMenu;
import com.xx.system.mapper.SysDictDetailMapper;
import com.xx.system.service.SysDictDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class SysDictDetailServiceImpl extends ServiceImpl<SysDictDetailMapper, SysDictDetail> implements SysDictDetailService {
    // Add
    @Override
    public void addDictDetail(SysDictDetail detail) {
        save(detail);
    }

    // Delete
    @Override
    public void deleteDictDetailById(Long detailId) {
        removeById(detailId);
    }

    // Update
    @Override
    public void updateDictDetail(SysDictDetail detail) {
        updateById(detail);
    }

    // Retrieve
    @Override
    public List<SysDictDetail> getAllDictDetails() {
        return list();
    }
    @Override
    public SysDictDetail getDictDetailById(Long detailId) {
        return getById(detailId);
    }
}
