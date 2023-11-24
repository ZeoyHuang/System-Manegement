package com.xx.tools.utils;

import java.util.List;

public class PageUtils {
    private int curPage = 0;                               // 当前显示页数
    private int pageCount = 0;                             // 总页数
    private int rowCount;                                  // 总记录数
    private int pageSize;                                  // 每页显示记录数
    private List subList;                                  // 每页要显示的集合

    // 每页显示记录数数组
    private int[] pageSizes = new int[] { 10, 20, 30, 50, 100 };

    // 是否显示每页显示记录数
    private boolean showPageSize = true;

    // 默认每页显示记录数
    public static final int defaultPageSize = 10;

    //默认当前页
    public static final int DEFAULT_CURRENT_PAGE = 1;

    public class PageControl {

        private int rowCount;
        private int curPage;
        private int pageSize;
        private int pageCount;

        private static final int defaultPageSize = 10; // Set your default page size here

        public PageControl() {
            this.curPage = 1;
            this.pageSize = defaultPageSize;
            this.countMaxPage();
        }

        public PageControl(int rowCount) {
            this(rowCount, defaultPageSize);
        }

        public PageControl(int rowCount, int pageSize) {
            this(rowCount, 1, pageSize);
        }

        public PageControl(int rowCount, int curPage, int pageSize) {
            this.rowCount = rowCount;
            this.curPage = (curPage <= 0) ? 1 : curPage;
            this.pageSize = pageSize;
            this.countMaxPage();
            if (this.curPage > this.pageCount) {
                this.curPage = this.pageCount;
            }
        }

        public PageControl(List<?> list) {
            this(list, defaultPageSize);
        }

        public PageControl(List<?> list, int pageSize) {
            this(list, 1, pageSize);
        }

        public PageControl(List<?> list, int curPage, int pageSize) {
            this.rowCount = (list != null) ? list.size() : 0;
            this.pageSize = pageSize;
            this.countMaxPage();
            this.curPage = (curPage <= 0 || curPage > this.pageCount) ? 1 : curPage;
            countList(list);
        }

        private void countMaxPage() {
            // Calculate pageCount based on rowCount and pageSize
        }

        private void countList(List<?> list) {
            // Count the list based on pagination settings
        }

        // Getter methods for rowCount, curPage, pageSize, pageCount, etc.
    }

    private void countList(List list)
    {

        if (null == list)
        {
            return;
        }
        int size = list.size();
        int begin = (curPage - 1) * pageSize;
        int end = begin + pageSize;

        if (end > size)
        {
            end = size;
        }

        this.subList = list.subList(begin, end);
    }

    /**
     * 计算总页数
     */
    private void countMaxPage()
    {
        if (rowCount % pageSize == 0)
        {
            pageCount = rowCount / pageSize;
        }
        else
        {
            pageCount = rowCount / pageSize + 1;
        }

        // if(rowCount == 0)
        // pageSize = 0;
    }

    /**
     * 获取每页显示集合
     *
     * @return
     */
    public List getSubList()
    {
        return this.subList;
    }

    /**
     * <设置每页显示集合对象>
     * <功能详细描述>
     * @param subList [参数说明]
     */
    public void setSubList(List subList)
    {
        this.subList = subList;
    }

    /**
     * 读取每页显示记录数
     *
     * @return int
     */
    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;

        this.countMaxPage();
        if (curPage > pageCount)
        {
            this.curPage = pageCount;
        }
    }

    /**
     * 读取当前显示页数
     *
     * @return int
     */
    public int getCurPage()
    {
        return curPage;
    }

    public void setCurPage(int curPage)
    {
        this.curPage = curPage;
    }

    /**
     * 读取总页数
     *
     * @return int
     */
    public int getPageCount()
    {
        return pageCount;
    }

    /**
     * 读取总记录数
     *
     * @return int
     */
    public int getRowCount()
    {
        return rowCount;
    }

    /**
     * 判断是否存在下一页
     *
     * @return boolean
     */
    public boolean isNextPageAvailable()
    {
        return curPage >= 1 && curPage < pageCount;
    }

    /**
     * 判断是否存在上一页
     *
     * @return boolean
     */
    public boolean isPreviousPageAvailable()
    {
        return curPage > 1 && curPage <= pageCount;
    }

    /**
     * 获取下一页
     *
     * @return
     */
    public int getNextPage()
    {
        int next = curPage + 1;
        if (pageCount < next)
        {
            next = pageCount;
        }
        return next;
    }

    /**
     * 获取前一页
     *
     * @return
     */
    public int getPrevPage()
    {
        int prev = curPage - 1;
        if (prev <= 1)
        {
            prev = 1;
        }
        return prev;
    }

    /**
     * 获取第一页
     *
     * @return
     */
    public int getFirstPage()
    {
        return 1;
    }

    /**
     * 获取最后一页
     *
     * @return
     */
    public int getLastPage()
    {
        return pageCount;
    }

    /**
     * 判断是否是首页
     *
     * @return
     */
    public boolean isFirstPage()
    {
        return (curPage == 1) ? true : false;
    }

    /**
     * 判断是否是尾页
     *
     * @return
     */
    public boolean isLastPage()
    {
        return (curPage == pageCount) ? true : false;
    }

    /**
     * 获取当前页面的开始记录
     *
     * @return
     */
    public int getBeginRecord()
    {
        int begingRecord = (this.curPage - 1) * this.pageSize;
        return (begingRecord <= 0 ? 0 : begingRecord);
    }

    /**
     * 获取分页开始值
     *
     * @param curPage
     *            int 当前页
     * @param pageSize
     *            int 每页的记录数
     * @return
     */
    public int getBeginRecord(int curPage, int pageSize)
    {
        return (curPage - 1) * pageSize;
    }

    public int[] getPageSizes()
    {
        //should return a clone of pageSizes.
        return (null == pageSizes ? null : pageSizes.clone());
    }

    public void setPageSizes(int[] pageSizes)
    {
        this.pageSizes = pageSizes;
    }

    public boolean isShowPageSize()
    {
        return showPageSize;
    }

    public void setShowPageSize(boolean showPageSize)
    {
        this.showPageSize = showPageSize;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }
}
