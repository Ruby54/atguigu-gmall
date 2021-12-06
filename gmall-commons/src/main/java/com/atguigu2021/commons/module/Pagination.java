package com.atguigu2021.commons.module;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-06 16:54
 */
public class Pagination {
    private int pageNo = 1;
    private int totalPage;
    private int totalRecord;
    private int pageSize = 10;
    private int start = -1;

    public Pagination() {
    }

    public Pagination(int pageNo, int pageSize) {
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 0) {
            throw new IllegalArgumentException("pageNo must be greater than 1");
        } else {
            this.pageNo = pageNo;
            this.start = -1;
        }
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        if (totalPage < 0) {
            throw new IllegalArgumentException("totalPage must be greater than or equal 0");
        } else {
            this.totalPage = totalPage;
        }
    }

    public int getTotalRecord() {
        return this.totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        if (totalRecord < 0) {
            throw new IllegalArgumentException("totalRecord must be greater than or equal 0");
        } else {
            this.totalRecord = totalRecord;
            this.totalPage = totalRecord / this.pageSize;
            if (totalRecord % this.pageSize > 0) {
                ++this.totalPage;
            }

            if (this.totalPage == 0) {
                this.pageNo = 1;
                this.start = -1;
            } else if (this.pageNo > this.totalPage) {
                this.pageNo = this.totalPage;
                this.start = -1;
            }

        }
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("pageSize must be greater than  0");
        } else {
            this.pageSize = pageSize;
        }
    }

    public int getStart() {
        if (this.start < 0) {
            this.start = (this.pageNo - 1) * this.pageSize;
        }

        return this.start;
    }
}
