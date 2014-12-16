package com.isbn.util;

public class QueryParams {

	private Integer pageSize = Constant.DEFAULT_PAGE_SIZE;
	
	private Integer pageNum = Constant.DEFAULT_PAGE_NUM;
	
	private Integer offset ;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getOffset() {
		if(pageNum < 2){
			return 0;
		}else{
			return (pageNum - 1) * pageSize;
		}
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
	
}
