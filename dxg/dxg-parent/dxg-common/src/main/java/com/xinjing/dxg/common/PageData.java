package com.xinjing.dxg.common;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 分页帮助类<br>
 */
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int size; // 每页显示记录数
    private int number; // 当前页
    private boolean firstPage; // 是否为第一页
    private boolean lastPage; // 是否为最后一页
    private int totalPages; // 总页数
    private Long total = 0L; // 数据总数
    private List<T> rows = new ArrayList<T>();

    public PageData() {
    }

    /**
     * 由于规定不能返回数据库实体给页面,此方法作废
     * @param pageData
     */
    @Deprecated
    public PageData(Page<T> pageData) {
        this.size = pageData.getSize();
        this.number = pageData.getNumber();
        this.lastPage = pageData.isLast();
        this.firstPage = pageData.isFirst();
        this.totalPages = pageData.getTotalPages();
        this.total = pageData.getTotalElements();
        this.rows = pageData.getContent();
    }

    public PageData(Page pageData,List<T> list) {
        this.size = pageData.getSize();
        this.number = pageData.getNumber();
        this.lastPage = pageData.isLast();
        this.firstPage = pageData.isFirst();
        this.totalPages = pageData.getTotalPages();
        this.total = pageData.getTotalElements();
        this.rows = list;
    }

    public PageData(int size, int number) {
        this(size, number, 0);
    }


    public PageData(int size, int number, long total) {
        this(size, number, total, new ArrayList<T>());
    }


    public PageData(int size, int number, long total, List<T> rows) {
        this.size = size;
        this.number = number;
        this.total = total;
        this.rows = rows;
        initPage();
    }

    public PageData(int size, int number, List<T> rows) {
        this.size = size;
        this.number = number;
        this.total = Long.valueOf(rows.size());
        initPage();
        int i = rows.size() / size + 1;
        if(rows.size() > 0 && number <= i){
            if(lastPage){
                this.rows = rows.subList(size*(number-1),rows.size());
            }else if(rows == null || rows.size() <= 0){
                this.rows = new ArrayList<>();
            }else{
                this.rows = rows.subList(size*(number-1),size*number);
            }
        }else{
            this.rows = new ArrayList<>();
        }
    }


    public PageData(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;
    }

    public void initPage() {
        this.totalPages = (total.intValue() + size - 1) / size;

        if (totalPages > 0 && number == 1) {
            this.firstPage = true;
        } else {
            this.firstPage = false;
        }

        if (totalPages > 0 && number == totalPages) {
            this.lastPage = true;
        } else {
            this.lastPage = false;
        }
    }

    public int getFirstResult() {
        return (number - 1) * size;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }


    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }


    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }


    @Override
    public String toString() {
        return "PageData [size=" + size + ", number=" + number + ", firstPage=" + firstPage + ", lastPage="
                + lastPage + ", totalPages=" + totalPages + ", total=" + total + "]";
    }

}
