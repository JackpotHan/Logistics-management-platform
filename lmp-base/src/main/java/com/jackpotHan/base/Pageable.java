package com.jackpotHan.base;

public class Pageable extends BaseObject{

    private Integer pageNum;
    private Integer pageSize;

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageNum() {

        return pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
