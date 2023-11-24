package com.xx.system.entity;

import lombok.Data;

import java.util.List;
@Data
public class PageSplit {
    //总条数
    private int total;
    //表格数据
    private List list;
    //当前页
    private int currentPage;
    //每页数
    private int pageSize;

    public PageSplit() {
    }

    public PageSplit(int total, List list, int currentPage, int pageSize) {
        this.total = total;
        this.list = list;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
