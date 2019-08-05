package com.jackpot.base.base;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Hanjt
 * @Date: 2018/8/2 10:33
 * @Description:
 */
public class BaseObject implements Serializable {

    private static final long serialVersionUID = -962667457706387227L;

    /**
     * 分页查询 当前页数.
     */
    @Transient
    public Integer pageNum;

    /**
     * 分页查询 每页显示多少条 .
     */
    @Transient
    public Integer pageSize;

    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date endTime;


    public Integer getPageNum() {
        return pageNum == null || pageNum == 0 ? 1 : pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null || pageSize == 0 ? 10 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
