package com.xinjing.dxg.common;

public class PagerParam {
    private Integer pageNumber = 1;
    private Integer pageSize = 10;
    private String sortName="1";
    private String sortOrder = "asc";
    private Integer nextPageStartRow = 0;

    /**
     * @return the nextPageStartRow
     */
    public Integer getNextPageStartRow() {
        return nextPageStartRow;
    }

    /**
     * @param nextPageStartRow the nextPageStartRow to set
     */
    public void setNextPageStartRow(Integer nextPageStartRow) {
        this.nextPageStartRow = nextPageStartRow;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

}
