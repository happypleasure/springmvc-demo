package com.isbn.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {

	public static final String LOGINED = "logined";

	public static final String ISBN_SUCCESS_CODE = "000000";
	
	public static final String ISBN_NO_RESULT = "000001";
	
	public static final String ISBN_NO_AUTH = "000002";
	
	public static final String ISBN_OR_PUBLISHERID_NULL = "000003";
	
	public static final String ISBN_FAIL_CODE = "000004";
	
	public static final Integer DEFAULT_PAGE_SIZE = 5;
	
	public static final Integer DEFAULT_PAGE_NUM = 1;
	
	public static final Map<String,String> LANGMAP = new HashMap<String,String>();
	
	public static final Map<String,String> BOOKTYPE = new HashMap<String,String>();
	static{
		LANGMAP.put("1", "汉语");
		LANGMAP.put("2", "英语");
		LANGMAP.put("3", "法语");
		LANGMAP.put("4", "俄语");
		LANGMAP.put("5","德语");
		LANGMAP.put("6","日语");
		LANGMAP.put("7", "西班牙语");
		LANGMAP.put("8", "阿拉伯语");
		LANGMAP.put("9 ","葡萄牙语");
		LANGMAP.put("10", "印第语");
		LANGMAP.put("11", "孟加拉语");
		LANGMAP.put("-1", "其他语种");
		
		
		BOOKTYPE.put("5", "教材");
		BOOKTYPE.put("13", "教辅");
		BOOKTYPE.put("23", "少儿读物");
		BOOKTYPE.put("26", "辞典工具书");
		BOOKTYPE.put("29", "文学");
		BOOKTYPE.put("35", "艺术");
		BOOKTYPE.put("39", "历史地理");
		BOOKTYPE.put("40", "社会科学");
		BOOKTYPE.put("41", "科学技术");
		BOOKTYPE.put("42", "政治");
		BOOKTYPE.put("43", "经济");
		BOOKTYPE.put("44", "哲学");
		BOOKTYPE.put("45", "法律");
		BOOKTYPE.put("46", "军事");
		BOOKTYPE.put("1", "其他");
		
	}
	/*		  <option value="5">教材</option>
    <option value="13">教辅</option>
    <option value="23">少儿读物</option>
    <option value="26">辞典工具书</option>
    <option value="29">文学</option>
    <option value="35">艺术</option>
    <option value="39">历史地理</option>
    <option value="40">社会科学</option>
    <option value="41">科学技术</option>
    <option value="42">政治</option>
    <option value="43">经济</option>
    <option value="44">哲学</option>
    <option value="45">法律</option>
    <option value="46">军事</option>
    <option value="1">其他</option>*/
	
	
/*	  <option value="1">汉语</option>
      <option value="2">英语</option>
      <option value="3">法语</option>
      <option value="4">俄语</option>
      <option value="5">德语</option>
      <option value="6">日语</option>
      <option value="7">西班牙语</option>
      <option value="8">阿拉伯语</option>
      <option value="9">葡萄牙语</option>
      <option value="10">印地语</option>
      <option value="11">孟加拉语</option>
      <option value="-1">其他语种</option>*/

}
