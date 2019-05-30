package com.trantrongnhan.paging;

public class PageRequest {
	private Integer offset;
	private Integer limit;
	public PageRequest(Integer offset,Integer limit) {
		this.offset=offset;
		this.limit=limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}
