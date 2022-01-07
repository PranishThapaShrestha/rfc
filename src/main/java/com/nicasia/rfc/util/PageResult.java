package com.nicasia.rfc.util;

import java.util.List;

public class PageResult<T> {

    private List<T> results;

    private int page;

    private long totalResult;

    private int totalPages;

    public PageResult() {

    }
    public PageResult(List<T>results,int page,long totalResult,int totalPages){
        this.results=results;
        this.page=page;
        this.totalResult=totalResult;
        this.totalPages=totalPages;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(long totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
