package com.isbn.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.isbn.pojo.Book;
import com.isbn.pojo.Publisher;
import com.isbn.service.IPublisherService;
import com.isbn.util.Constant;
import com.isbn.util.QueryParams;
import com.isbn.util.ReturnObj;

@Service("publisherService")
public class PublisherService implements IPublisherService{

	@Resource
	private SqlSession sqlSession;
	
	private static final String MAPPER_NAMESPACE = "com.isbn.service.impl.mapper.";
	
	public Publisher login(Publisher publisher) throws Exception { 
		publisher = sqlSession.selectOne(MAPPER_NAMESPACE + "loginFind" , publisher);
		return publisher;
	}

	public ReturnObj queryBookList(QueryParams queryParams) throws Exception {
		
		int total = sqlSession.selectOne(MAPPER_NAMESPACE + "getBookTotal");
		
		List<Book> bookList = 
				sqlSession.selectList(MAPPER_NAMESPACE + "queryBookList", queryParams);
		
		return new ReturnObj(Constant.ISBN_SUCCESS_CODE,total,bookList);
	}

	public int addBook(Book book) throws Exception {
		String isbn = getIncreaseIsbn();
		book.setIsbn(isbn);
		return sqlSession.insert(MAPPER_NAMESPACE + "addBook",book);
	}
	
	public static Integer INIT_RANDROM_ISBN_NUM = 12345678;

	private  String getIncreaseIsbn() {
		Random rd = new Random();
		int lastNum = rd.nextInt(10);
		INIT_RANDROM_ISBN_NUM++;
		int pointcut = rd.nextInt(8);
		if(pointcut == 0){
			pointcut = 4;
		}
		StringBuilder sb = new StringBuilder(INIT_RANDROM_ISBN_NUM.toString());
		sb.insert(pointcut, ',');
		return "978,7,"+sb.toString()+","+lastNum;
	}
	
	public Book queryBookDetailById(Integer id) throws Exception {
		Book book = sqlSession.selectOne(MAPPER_NAMESPACE + "queryBookDetailById",id); 
		/**
		 * 语种
		 */
		String lang = book.getLangId();
		if(StringUtils.hasText(lang)){
			book.setLangId(Constant.LANGMAP.get(lang));
		}
		
		/**
		 * 开本
		 */
		String format = book.getFormat();
		if(StringUtils.hasText(format)){
			String[] s = format.split(",");
			String s0 = s[0];
			String s1 = s[1];
			String s2 = s[2];
			String ss = "";
			if(StringUtils.hasText(s0)){
				ss += s0 + "mm";
			}
			if(StringUtils.hasText(s1)){
				ss +=  "  x   "+ s1 + "mm ";
			}
			if(StringUtils.hasText(s2)){
				ss +=  "(1/"+ s2 +")";
			}
			book.setFormat(ss);
		}
		
		/**
		 * 类别
		 */
		String bookType = book.getBookContentTypeId();
		if(StringUtils.hasText(bookType)){
			book.setBookContentTypeId(Constant.BOOKTYPE.get(bookType));
		}
		
		/**
		 * 定价
		 */
		String priceType = book.getPriceType();
		if(StringUtils.hasText(priceType)){
			String[] strArr = priceType.split(",");
			String num = strArr[0];
			String type = strArr[1];
			String count = strArr[2];
			String s = ""; 
			if(StringUtils.hasText(num)){
				s += num;
				if(type.contains("￥")){
					s += "人民币";
				}else if(type.contains("HK$")){
					s += "港币";
				}else if(type.contains("$")){
					s += "美元";
				}
			}
			
			if(StringUtils.hasText(count)){
				s += "   共"+count + "本";
			}
			book.setPriceType(s);
		}
		
		/**
		 * 印次
		 */
		String printerCount = book.getPrinterCount();
		if(StringUtils.hasText(printerCount)){
			String[] strArr =printerCount.split(",");
			String year  = strArr[0];
			String month  = strArr[1];
			String count  = strArr[2];
			String string = "";
			if(StringUtils.hasText(year)){
				string += year +"年";
			}
			if(StringUtils.hasText(month)){
				string += " "+month +"月  ";
			}
			if(StringUtils.hasText(count)){
				string += "第"+ count +"次印刷";
			}
			book.setPrinterCount(string);
		}
		
		String isbn = book.getIsbn();
		book.setIsbn(isbn.replace(',', '-'));
		return book;
	}

	public int auth(Publisher publisher) throws Exception {
		publisher = login(publisher);
		if(publisher == null || publisher.getId() == null){
			return -1;
		}
		return sqlSession.update(MAPPER_NAMESPACE + "auth",publisher.getId());
	}

	public ReturnObj queryBook(Book book) throws Exception {
		book = sqlSession.selectOne(MAPPER_NAMESPACE + "queryBook", book);
		if(book != null && book.getId() != null){
			return new ReturnObj(Constant.ISBN_SUCCESS_CODE,"查询成功",book);
		}else{
			return new ReturnObj(Constant.ISBN_NO_RESULT,"ISBN编号不存在",book);
		}
	}
}
