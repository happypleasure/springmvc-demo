package com.isbn.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;


public class Book {

	private Integer id;
	private String bookNameCn;
	private String seriesName;
	private String bookContentTypeId;
	private String langId;
	private String editing;
	private String format;
	private String priceType;
	private String pages;
	private String wordage;
	private String printerCount;
	private Date prepareTime;
	private String isbn;
	private String prepareTimeStr;
	private String publisherId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookNameCn() {
		return bookNameCn;
	}
	public void setBookNameCn(String bookNameCn) {
		this.bookNameCn = bookNameCn;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public String getBookContentTypeId() {
		return bookContentTypeId;
	}
	public void setBookContentTypeId(String bookContentTypeId) {
		this.bookContentTypeId = bookContentTypeId;
	}
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	public String getEditing() {
		return editing;
	}
	public void setEditing(String editing) {
		this.editing = editing;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getWordage() {
		return wordage;
	}
	public void setWordage(String wordage) {
		this.wordage = wordage;
	}
	public String getPrinterCount() {
		return printerCount;
	}
	public void setPrinterCount(String printerCount) {
		this.printerCount = printerCount;
	}
	public Date getPrepareTime() {
		if(prepareTime == null && StringUtils.hasText(prepareTimeStr)){
			try {
				prepareTime = new SimpleDateFormat("yyyy-MM-dd").parse(prepareTimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return prepareTime;
	}
	public void setPrepareTime(Date prepareTime) {
		this.prepareTime = prepareTime;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPrepareTimeStr() {
		if(!StringUtils.hasText(prepareTimeStr) && prepareTime != null){
			prepareTimeStr = new SimpleDateFormat("yyyy-MM-dd").format(prepareTime);
		}
		return prepareTimeStr;
	}
	public void setPrepareTimeStr(String prepareTimeStr) {
		this.prepareTimeStr = prepareTimeStr;
	}
	
	public String getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookNameCn=" + bookNameCn
				+ ", seriesName=" + seriesName + ", bookContentTypeId="
				+ bookContentTypeId + ", langId=" + langId + ", editing="
				+ editing + ", format=" + format + ", priceType=" + priceType
				+ ", pages=" + pages + ", wordage=" + wordage
				+ ", printerCount=" + printerCount + ", prepareTime="
				+ prepareTime + ", isbn=" + isbn + ", prepareTimeStr="
				+ prepareTimeStr + ", publisherId=" + publisherId + "]";
	}
	
	
}
