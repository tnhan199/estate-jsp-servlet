package com.trantrongnhan.paging;

public class PageRequest implements Pageable {
	private Integer page;
	private Integer maxPageItem;
	public PageRequest(Integer page,Integer maxPageItem) {
		this.maxPageItem=maxPageItem;
		this.page=page;
	}
	@Override
	public Integer getPage() {
		return this.page;
	}
	@Override
	public Integer getOffset() {
		return (page-1)*maxPageItem;
	}
	@Override
	public Integer getLimit() {
		// TODO Auto-generated method stub
		return this.maxPageItem;
	}

}
