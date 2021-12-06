package com.atguigu2021.commons.module;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-06 16:43
 */
public class PageResponse {
    private Query query;
    private Pagination pagination;

    public PageResponse() {
    }

    public PageResponse(Pagination pagination, Query query) {
        this.query = query;
        this.pagination = pagination;
    }

    public Query getQuery() {
        return this.query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Pagination getPagination() {
        return this.pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
