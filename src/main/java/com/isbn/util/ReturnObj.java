package com.isbn.util;

public class ReturnObj {

	private String errorCode; 
	
	private String errorMsg;
	
	private Integer total;
	
	private Object data;
	
	private Integer totalPage;
	
	
	public ReturnObj(String errorCode , String errorMsg , Object data){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.data = data;
	}
	
	public ReturnObj(String errorCode , Integer total , Object data){
		this.errorCode = errorCode;
		this.total = total;
		this.data = data;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Integer getTotalPage() {
		if(total != null){
			totalPage = (total%Constant.DEFAULT_PAGE_SIZE == 0) ?
						total/Constant.DEFAULT_PAGE_SIZE : 
						(total/Constant.DEFAULT_PAGE_SIZE + 1);
		}
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
}
