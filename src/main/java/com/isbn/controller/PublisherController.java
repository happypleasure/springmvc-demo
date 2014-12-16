package com.isbn.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isbn.pojo.Book;
import com.isbn.pojo.Publisher;
import com.isbn.service.IPublisherService;
import com.isbn.util.Constant;
import com.isbn.util.CryptUtil;
import com.isbn.util.Encrypt;
import com.isbn.util.QueryParams;
import com.isbn.util.ReturnObj;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

	private static final Logger logger = LoggerFactory
			.getLogger(PublisherController.class);

	@Resource
	private IPublisherService publisherService;
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @param formAccountId
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Publisher login(HttpServletRequest request,
			HttpServletResponse response,
			String formAccountId,
			String password) {
		
		if (StringUtils.hasText(formAccountId) && StringUtils.hasText(password)) {
			Publisher publisher = new Publisher();
			String md5Pwd = getMd5Password(password);
			publisher.setPassword(md5Pwd);
			if (formAccountId.trim().matches("^[0-9_]+$")) {
				publisher.setAccountId(formAccountId);
			} else if (formAccountId.trim().indexOf("@") > -1) {
				publisher.setEmail(formAccountId);
			} else {
				publisher.setUserName(formAccountId);
			}
			try {
				publisher = publisherService.login(publisher);
				if(publisher != null){
					request.getSession().setAttribute(Constant.LOGINED, publisher);
				}
				return publisher;
			} catch (Exception e) {
				logger.error("error cause by",e);
			}
		} 
		return null;
	}
	
	
	/**
	 * 获取列表
	 * @param password
	 * @return
	 */
	@RequestMapping("/queryBookList")
	@ResponseBody
	public ReturnObj queryBookList(QueryParams queryParams){
		try{
			ReturnObj rtnObj = publisherService.queryBookList(queryParams);
			return rtnObj;
		}catch(Exception e){
		}
		return null;
	}
	

	/**
	 * 图书注册
	 * @param response
	 * @param book
	 */
	@RequestMapping(value = "/register" , method=RequestMethod.POST)
	public void addBook(HttpServletRequest request,HttpServletResponse response, Book book){
		try{
			Publisher publisher = (Publisher) request.getSession().getAttribute(Constant.LOGINED);
			book.setPublisherId(publisher.getAccountId());
			int count = publisherService.addBook(book);
			response.getWriter().print(count);
		}catch(Exception e){
		}
	}
	
	
	/**
	 * 图书注册
	 * @param response
	 * @param book
	 */
	@RequestMapping("/queryBookDetail")
	@ResponseBody
	public Book queryBookDetail(HttpServletResponse response,Integer id){
		try{
			Book book = publisherService.queryBookDetailById(id);
			return book;
		}catch(Exception e){
		}
		return null;
	}
	
	
	@RequestMapping("/auth")
	@ResponseBody
	public void auth(HttpServletRequest request,
			HttpServletResponse response,
			String formAccountId,
			String password){
		if (StringUtils.hasText(formAccountId) && StringUtils.hasText(password)) {
			Publisher publisher = new Publisher();
			String md5Pwd = getMd5Password(password);
			publisher.setPassword(md5Pwd);
			if (formAccountId.trim().matches("^[0-9_]+$")) {
				publisher.setAccountId(formAccountId);
			} else if (formAccountId.trim().indexOf("@") > -1) {
				publisher.setEmail(formAccountId);
			} else {
				publisher.setUserName(formAccountId);
			}
			try {
				int result = publisherService.auth(publisher);
				response.getWriter().print(result);
			} catch (Exception e) {
				logger.error("error cause by",e);
			}
		} 
	}
	
	
	@RequestMapping("/queryParamsByIsbn")
	@ResponseBody
	public ReturnObj getParamsByIsbn(@RequestParam("isbn") String isbn, @RequestParam("publisherId") String publisherId){
		try{
			//978,7,12345,321,1
			if (StringUtils.hasText(isbn) && StringUtils.hasText(publisherId)) {
				Book book = new Book();
				book.setPublisherId(publisherId);
				return publisherService.queryBook(book);
			}else{
				return new ReturnObj(Constant.ISBN_OR_PUBLISHERID_NULL,"isbn获取publisherId为空",null);
			}
		}catch(Exception e){
		}
		return null;
	}
	
	 private String getMd5Password(String password)
	    {
	        
	        if (password == null)
	        {
	            return null;
	        }
	        Encrypt encrypt = new com.isbn.util.MD5Encrypt();
	        try
	        {
	            password =
	                CryptUtil.encodeBytes(encrypt.encrypt(password.getBytes("UTF-8"),
	                    null));
	        }
	        catch (Exception e1)
	        {
	            logger.error("密码加密错误: " + password);
	            e1.printStackTrace();
	        }
	        return password;
	    }

}
