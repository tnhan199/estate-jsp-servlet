package com.trantrongnhan.paging;

public interface Pageable {
    Integer getPage();

    Integer getOffset();

    Integer getLimit();
}
